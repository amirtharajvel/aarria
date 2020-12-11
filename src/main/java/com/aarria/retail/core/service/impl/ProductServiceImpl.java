package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.*;
import com.aarria.retail.core.exception.UnexpectedException;
import com.aarria.retail.core.service.*;
import com.aarria.retail.core.util.Enum.ProductDescriptionType;
import com.aarria.retail.core.util.Enum.Refiners;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.business.ProductPrice;
import com.aarria.retail.persistence.repository.ProductRepository;
import com.aarria.retail.persistence.repository.ProductStockRepository;
import com.aarria.retail.persistence.repository.custom.ProductsWithAttributeRepository;
import com.aarria.retail.web.dto.request.*;
import com.aarria.retail.web.dto.response.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

	private static final Integer NO_OF_PRODUCTS_PER_PAGE = 60;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductStockRepository productStockRepository;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductsWithAttributeRepository productRepositoryCustom;

	@Autowired
	private ProductImageService productColorsService;

	@Autowired
	private ImageUploadService imageUploadService;

	@Override
	public Product findByPidWithStock(String pid) {
		return productRepository.findByPid(pid);
	}

	@Override
	public ProductListDto findByCategoryIds(GetProductsInputDto input, Integer page) {

		page--;

		Double minPrice = 0d;
		Double maxPrice = 100000000d;

		if (input.getMinPrice() != null) {
			minPrice = input.getMinPrice().doubleValue();
		}

		if (input.getMaxPrice() != null) {
			maxPrice = input.getMaxPrice().doubleValue();
		}

		Direction sortOrder = Util.getSortOrder(input.getInternalSort());
		String sortColumn = Util.getSortColumn(input.getInternalSort());
		PageRequest pageRequest = PageRequest.of(page, NO_OF_PRODUCTS_PER_PAGE, sortOrder, sortColumn, "id");
		ProductListDto dto = new ProductListDto();

		boolean doNotExcludeSoldOut = input.getExcludeSoldOut() == null || input.getExcludeSoldOut().equals(0);

		boolean excludeSoldOut = input.getExcludeSoldOut() == null ? false : input.getExcludeSoldOut().equals(1);

		if (input.getAttributes().contains(-1L) && input.getAttributes().size() == 1) {

			List<Object[]> count = null;
			if (doNotExcludeSoldOut) {
				count = productRepository.findCountByCategoryIds(input.getCategories(), minPrice, maxPrice);
			} else if (excludeSoldOut) {
				count = productRepository.findCountByCategoryIdsWithStock(input.getCategories(), minPrice, maxPrice);
			}

			Double min = 100000d;
			Double max = 0d;

			for (Object[] obj : count) {
				Double localMax = (Double) obj[2];
				Double localMin = (Double) obj[3];

				if (localMax > max) {
					max = localMax;
				}

				if (localMin < min) {
					min = localMin;
				}
			}

			dto.setMaxPrice(max);
			dto.setMinPrice(min);
			double size = count.size();
			double noOfProducts = Double.valueOf(NO_OF_PRODUCTS_PER_PAGE);
			dto.setPageCount((int) Math.ceil(size / noOfProducts));
			dto.setResultCount((int) size);

			List<Product> products = null;

			if (doNotExcludeSoldOut) {
				products = productRepository.findByCategoryIds(input.getCategories(), minPrice, maxPrice, pageRequest);
			} else if (excludeSoldOut) {
				products = productRepository.findByCategoryIdsWithStock(input.getCategories(), minPrice, maxPrice,
						pageRequest);
			}

			dto.setProducts(products);

		} else {
			Set<Attribute> attributes = attributeService.findByIds(input.getAttributes());

			Map<String, List<Long>> map = new HashMap<>();
			if (CollectionUtils.isNotEmpty(attributes)) {

				for (Attribute attribute : attributes) {
					List<Long> attributeIds = map.get(attribute.getRefiner());
					if (attributeIds == null) {
						List<Long> attr = new ArrayList<>();
						attr.add(attribute.getId());
						map.put(attribute.getRefiner(), attr);

					} else {
						attributeIds.add(attribute.getId());
					}
				}
			} else {
				return null;
			}

			String categoryIds = StringUtils.join(input.getCategories(), ",");

			String countQuery = "select distinct count(distinct p), min(p.price), max(p.price) from Product p inner join p.productStock ps join p.categories cs where cs.id in("
					+ categoryIds + ") and p.id in ";

			String query = "select distinct p from Product p inner join p.productStock ps join p.categories cs where cs.id in("
					+ categoryIds + ") and p.id in ";

			int i = 0;
			int size = map.entrySet().size();
			for (Map.Entry<String, List<Long>> uniqueAttribute : map.entrySet()) {
				String q = "";
				String as = "pa" + i;
				q += "(select " + as + ".product.id from ProductAttribute " + as + " join " + as + ".attribute " + as
						+ "attr where " + as + "attr.id in (" + StringUtils.join(uniqueAttribute.getValue(), ',') + ")";
				if (size != (++i)) {
					q += " and p.id in ";
				}

				query += q;
				countQuery += q;
			}

			for (int j = 0; j < size; j++) {
				query += ") ";
				countQuery += ")";

				if (j == (size - 1)) {
					query += " and p.price between " + minPrice + " AND  " + maxPrice + " ";

					if (excludeSoldOut) {
						query += " and ps.stock > 0 ";
						countQuery += " and ps.stock > 0 ";
					}

					query += "order by " + sortColumn + " " + sortOrder + ", p.id " + sortOrder;
				}
			}

			ProductsWithAttributesDto productsDto = productRepositoryCustom.findProductsWithAttributes(query,
					countQuery, NO_OF_PRODUCTS_PER_PAGE, page);

			if (productsDto == null) {
				return null;
			}

			double size1 = productsDto.getCount();
			double noOfProducts = Double.valueOf((NO_OF_PRODUCTS_PER_PAGE));

			dto.setProducts(productsDto.getProducts());
			dto.setPageCount((int) Math.ceil(size1 / noOfProducts));
			dto.setResultCount((int) size1);
			dto.setMinPrice(productsDto.getMinPrice());
			dto.setMaxPrice(productsDto.getMaxPrice());
		}

		int size = dto.getProducts() == null ? 0 : dto.getProducts().size();

		LOGGER.info("In Service layer showing " + size + " products");

		return dto;

	}

	@CacheEvict(value = "refiners", key = "#input.categories", allEntries = true)
	public void evictRefinersCache() {

	}

	@Override
	@Cacheable(value = "refiners", key = "#input.categories")
	public FilterDto getAllRefinersByCategory(GetProductsInputDto input) {

		Double minPrice = 0d;
		Double maxPrice = 100000000d;

		if (input.getMinPrice() != null) {
			minPrice = input.getMinPrice().doubleValue();
		}

		if (input.getMaxPrice() != null) {
			maxPrice = input.getMaxPrice().doubleValue();
		}

		Set<Long> catIds = new HashSet<>();

		if (CollectionUtils.isNotEmpty(input.getCategories())) {
			catIds.addAll(input.getCategories());
		} else {
			return null;
		}

		Set<Attribute> list = attributeService.findByCategory(catIds);

		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		Map<String, RefinerDto> map = new HashMap<>();
		for (Attribute attribute : list) {

			AttributeDto attributeDto = new AttributeDto(attribute);
			if (map.get(attribute.getRefiner()) == null) {

				List<AttributeDto> attributes = new ArrayList<>();
				attributes.add(attributeDto);

				RefinerDto refinerDto = new RefinerDto(WordUtils.capitalizeFully(attribute.getRefiner()), attributes);
				map.put(attribute.getRefiner(), refinerDto);

			} else {
				RefinerDto dto = map.get(attribute.getRefiner());

				boolean isDuplicate = false;
				for (AttributeDto a : dto.getUniqueAttributes()) {
					if (a.getValue() != null && a.getValue().equals(attribute.getValue())) {
						isDuplicate = true;
					}
				}

				if (isDuplicate == false) {
					dto.getUniqueAttributes().add(attributeDto);
				}
			}

		}

		FilterDto dto = new FilterDto(minPrice.intValue(), maxPrice.intValue(), new ArrayList<>(map.values()));

		return dto;

	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void save(AddProductDto dto) throws IOException {

		validate(dto);

		Product product = productRepository.save(dto.getProduct());

		if (product == null || product.getId() == null) {
			throw new UnexpectedException("Could not able to save product");
		}

		fixPrice(dto, product);

		saveAttributes(dto, product);

		imageUploadService.uploadAllProductImagesFromAddProducts(dto, product);

		saveDescription(dto, product);

		saveShippingAndReturnsDescription(dto, product);

		saveSizewiseStock(dto, product);

		productRepository.save(product);

	}

	@Override
	public FetchProductImagesDto getProductImages(Long productId) {

		Product product = productRepository.findById(productId).get();

		if (product == null) {
			return null;
		}

		List<ProductDetailsImageDto> images = productColorsService.getColorDetails(product);

		FetchProductImagesDto dto = new FetchProductImagesDto(product, images);

		return dto;

	}

	private void saveShippingAndReturnsDescription(AddProductDto dto, Product product) {
		Set<Description> descriptions = new HashSet<>();

		List<DescriptionDto> dtos = dto.getShippingAndReturns();
		if (dtos != null && !dtos.isEmpty()) {
			for (DescriptionDto desc : dtos) {
				if (StringUtils.isNotEmpty(desc.getKey())) {
					Description description = new Description();
					description.setKey(desc.getKey());
					description.setDescription(desc.getValue());
					description.setProduct(product);
					description.setType(ProductDescriptionType.SHIP_AND_RETURNS.name());

					descriptions.add(description);
				}
			}
		}

		product.getDescription().addAll(descriptions);
	}

	private void fixPrice(AddProductDto dto, Product product) {
		Double price = ProductPrice.getFinalPrice(product).doubleValue();

		product.setPrice(price);
		product.setDiscount(Util.calculateDiscount(product.getActualPrice(), price));
	}

	private void saveSizewiseStock(AddProductDto dto, Product product) {
		List<SizewiseStockDto> sizewiseDtos = dto.getSizewiseStock();
		List<ProductStock> productStocks = new ArrayList<>();
		for (SizewiseStockDto stockAndSize : sizewiseDtos) {
			if (!stockAndSize.getKey().trim().equals("")) {
				ProductStock stock = new ProductStock(stockAndSize, product);
				productStocks.add(stock);
			}
		}

		productStockRepository.saveAll(productStocks);
	}

	private void saveDescription(AddProductDto dto, Product product) {

		Set<Description> descriptions = new HashSet<>();

		List<DescriptionDto> dtos = dto.getDescription();
		if (dtos != null && !dtos.isEmpty()) {
			for (DescriptionDto desc : dtos) {
				if (StringUtils.isNotEmpty(desc.getKey())) {
					Description description = new Description();
					description.setKey(desc.getKey());
					description.setDescription(desc.getValue());
					description.setProduct(product);
					description.setType(ProductDescriptionType.PRODUCT_DESC.name());

					descriptions.add(description);
				}
			}
		}

		product.setDescription(descriptions);
	}

	private void validate(AddProductDto dto) {

		if (dto.getListingThumb() == null || dto.getListingThumb().getSize() <= 0) {
			throw new UnexpectedException("You must upload a listing thumbnail");
		}

		validateFile(dto.getListingThumb(), "listing thumbnail");

		MultipartFile[] colors = dto.getAdditionalImages();
		if (colors == null || colors.length < 0) {
			throw new UnexpectedException("Add color images or default images");
		}

		for (MultipartFile file : colors) {
			validateFile(file, "one of the additional color images");
		}

	}

	private void saveAttributes(AddProductDto dto, Product product) {

		Set<Attribute> attributes = getColors(dto, product);

		saveSizes(dto, product, attributes);

		saveSearchaleAttributes(dto, product, attributes);

		product.setPid(UUID.randomUUID().toString() + product.getId());

	}

	private void saveSearchaleAttributes(AddProductDto dto, Product product, Set<Attribute> attributes) {
		List<SearchableAttributeDto> searchableAttributes = dto.getSearchableAttributes();

		if (!CollectionUtils.isEmpty(searchableAttributes)) {
			searchableAttributes.forEach(s -> {

				if (StringUtils.isNotEmpty(s.getKey()) && StringUtils.isNotEmpty(s.getValue())) {

					List<Attribute> attributesFromDb = attributeService.findByValueAndRefinerAndCategory(
							s.getKey().trim(), s.getValue().trim(), dto.getCategories());

					Attribute existingAttribute = CollectionUtils.isNotEmpty(attributesFromDb) ? attributesFromDb.get(0)
							: null;

					Set<Category> categories = new HashSet<>();
					for (Long id : dto.getCategories()) {
						Category c = categoryService.findActiveCategories(id);
						c.setId(id);
						categories.add(c);
					}

					if (existingAttribute == null) {
						for (Category c : categories) {
							Attribute attribute = new Attribute();
							attribute.setRefiner(WordUtils.capitalizeFully(s.getKey().trim()));
							attribute.setValue(WordUtils.capitalizeFully(s.getValue().trim()));
							attribute.setCategory(c);

							existingAttribute = attributeService.save(attribute);
						}
					}

					if (existingAttribute == null) {
						throw new RuntimeException("existingAttribute is null on save!");
					}

					ProductAttribute productAttribute = new ProductAttribute();
					productAttribute.setAttribute(existingAttribute);
					productAttribute.setProduct(product);

					productAttributeService.save(productAttribute);
				}
			});
		}
	}

	private void saveSizes(AddProductDto dto, Product product, Set<Attribute> attributes) {
		List<String> sizes = dto.getSizes();
		if (sizes != null && !sizes.isEmpty()) {
			Set<String> uniques = new LinkedHashSet<>(sizes);

			for (String size : uniques) {
				if (StringUtils.isNotEmpty(size)) {

					List<Attribute> attributesFromDb = attributeService
							.findByValueAndRefinerAndCategory(Refiners.SIZE.name(), size.trim(), dto.getCategories());

					Attribute existingAttribute = CollectionUtils.isNotEmpty(attributesFromDb) ? attributesFromDb.get(0)
							: null;

					Set<Category> categories = new HashSet<>();
					for (Long id : dto.getCategories()) {
						Category c = categoryService.findActiveCategories(id);
						c.setId(id);
						categories.add(c);
					}

					if (existingAttribute == null) {
						for (Category c : categories) {
							Attribute attribute = new Attribute();
							attribute.setRefiner(Refiners.SIZE.name());
							attribute.setValue(size.trim());
							attribute.setCategory(c);

							existingAttribute = attributeService.save(attribute);
						}
					}

					if (existingAttribute == null) {
						throw new RuntimeException("existingAttribute is null on save!");
					}

					ProductAttribute productAttribute = new ProductAttribute();
					productAttribute.setAttribute(existingAttribute);
					productAttribute.setProduct(product);

					productAttributeService.save(productAttribute);

				}
			}
		}
	}

	private Set<Attribute> getColors(AddProductDto dto, Product product) {
		List<String> colors = dto.getColors();
		Set<Attribute> attributes = new HashSet<>();
		if (colors != null && !colors.isEmpty()) {
			for (String color : colors) {
				if (StringUtils.isNotEmpty(color)) {

					Set<Category> categories = new HashSet<>();
					Set<Long> ids = new HashSet<>();
					for (Long id : dto.getCategories()) {
						Category c = categoryService.findActiveCategories(id);
						c.setId(id);
						categories.add(c);
						ids.add(c.getId());
					}

					List<Attribute> attributesFromDb = attributeService
							.findByValueAndRefinerAndCategory(Refiners.COLOR.name(), color.trim(), ids);

					Attribute existingAttribute = CollectionUtils.isNotEmpty(attributesFromDb) ? attributesFromDb.get(0)
							: null;

					if (existingAttribute == null) {
						Attribute attribute = new Attribute();
						attribute.setRefiner(Refiners.COLOR.name());
						attribute.setValue(color.trim());
						attribute.setCategory(categories.iterator().next());

						existingAttribute = attributeService.save(attribute);
					}

					if (existingAttribute == null) {
						throw new RuntimeException("existingAttribute is null on save!");
					}

					ProductAttribute productAttribute = new ProductAttribute();
					productAttribute.setAttribute(existingAttribute);
					productAttribute.setProduct(product);

					productAttributeService.save(productAttribute);

				}
			}
		}

		return attributes;
	}

	private void validateFile(MultipartFile file, String image) {
		if (file != null && file.getSize() != 0 && file.getSize() < 500) {
			throw new UnexpectedException(
					"You are uploading a very small picture for " + image + " - size must be atleast 700 bytes");
		}

		if (file != null && file.getSize() > 10485760) {
			throw new UnexpectedException("Only 10 MB files are allowed");
		}

		if (file != null && file.getSize() != 0
				&& !(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
			throw new UnexpectedException("Only jpeg and png files are allowed to upload for " + image);
		}
	}

	@SuppressWarnings("unused")
	private static File createThumbnail(File file) {

		int height = 400, width = 400;

		try {
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			img.createGraphics().drawImage(ImageIO.read(Files.newInputStream(Paths.get(file.getPath())))
					.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
			// BufferedImage im = ImageIO.read(new
			// File(getClass().getResource("/resources/image.jpg").toURI()));

			File thumbnail = new File("/Users/hasikasri/Desktop/conversion/preview.jpg");
			// LOGGER.info("Creating thumbnail absolute path = " + file.getAbsolutePath() +
			// ", path = " + file.getPath());
			ImageIO.write(img, "jpg", thumbnail);
			return thumbnail;
		} catch (Exception e) {
			// LOGGER.error("Unable to create thumbnail ", e);
			return null;
		}
	}

	public static void main(String[] args) {
		File file = new File("/Users/hasikasri/Desktop/conversion/original.jpg");
		createThumbnail(file);

	}

	@Override
	public List<String> getMeasurementUnits() {
		return productRepository.getMeasurementUnits();
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public Set<Product> findAllProductsOrderByAddedDate() {
		return productRepository.findAllProductsOrderByAddedDate();
	}

	@Override
	public Set<Product> findProductsByIds(List<Long> ids) {
		return productRepository.findProductsByIds(ids);
	}

	@Override
	public Set<Product> getPopularProducts() {
		return productRepository.getPopularProducts();
	}

	@Override
	public Set<Product> getBestOfferProducts() {
		return productRepository.getBestOfferProducts();
	}

	@Override
	public Product findByProductPid(String pid) {
		return productRepository.findByPid(pid);
	}

	@Override
	public List<DisplayOffersInAddProductDto> getOffers() {
		List<Product> all = productRepository.findAll();
		List<DisplayOffersInAddProductDto> dtos = new ArrayList<>();
		Set<String> offerCodes = new HashSet<>();
		if (CollectionUtils.isNotEmpty(all)) {
			for (Product p : all) {
				if (offerCodes.add(p.getOfferCode())) {
					DisplayOffersInAddProductDto dto = new DisplayOffersInAddProductDto(p.getOfferText(),
							p.getOfferQuantity(), p.getOfferCode());
					dtos.add(dto);
				}
			}
		}
		return dtos;
	}

	@Override
	public boolean deleteProduct(Long productId) {

		productRepository.deleteById(productId);

		return true;
	}

	@Override
	public Set<Product> findSoldProducts() {
		return productRepository.findSoldOutProducts();
	}

	public static void fetchFiles(File dir, Consumer<File> fileConsumer) {

		if (dir.isDirectory()) {
			for (File file1 : dir.listFiles()) {
				fetchFiles(file1, fileConsumer);
			}
		} else {
			fileConsumer.accept(dir);
		}
	}

	public static void main_old(String[] args) throws IOException {

		File file = new File("/Users/hasikasri/Desktop/resources-work/resources/");
		fetchFiles(file, f -> {
			try {
				// System.out.println("getCanonicalPath=" + f.getCanonicalPath());
				// System.out.println("getName" + f.getName());
				// System.out.println("getPath" + f.getPath());
				System.out.println("getAbsolutePath = " + f.getAbsolutePath());

				if (!f.getAbsolutePath().contains("##")) {
					return;
				}

				// f.renameTo(new File(removeFileName(f.getAbsolutePath())));

				// compress(f.getAbsolutePath(), f.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	public static void compress(String input, String output) throws IOException {

		// File imageFile = new File("/Users/hasikasri/Desktop/reduce.png");
		// File compressedImageFile = new
		// File("/Users/hasikasri/Desktop/reduce.png");

		if (input.contains("ADDITIONAL")) {
			return;
		}

		if (!input.contains("##")) {
			return;
		}

		// output = changeFileName(output);
		// output = removeFileName(output);

		File imageFile = new File(input);
		File compressedImageFile = new File(output);

		// imageFile.renameTo(output);

		InputStream is = new FileInputStream(imageFile);
		OutputStream os = new FileOutputStream(compressedImageFile);

		float quality = 1.0f;

		// create a BufferedImage as the result of decoding the supplied InputStream
		BufferedImage image = ImageIO.read(is);

		// get all image writers for JPG format
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

		if (!writers.hasNext()) {
			os.close();
			throw new IllegalStateException("No writers found");
		}

		ImageWriter writer = writers.next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();

		// compress to a given quality
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);

		if (image == null) {
			System.out.println("Image is null " + imageFile);
			return;
		}

		// appends a complete image stream containing a single image and
		// associated stream and image metadata and thumbnails to the output
		writer.write(null, new IIOImage(image, null, null), param);

		// close all streams
		is.close();
		os.close();
		ios.close();
		writer.dispose();
	}

	private static String removeFileName(String output) {

		String o = output.replaceAll("#", "");
		System.out.println("result is " + o);
		return o;
	}

	@SuppressWarnings("unused")
	private static String changeFileName(String output) {
		String o = output.substring(output.lastIndexOf('.'), output.length());
		o = output.substring(0, output.lastIndexOf('.')) + "###" + o;
		System.out.println("result is " + o);

		return o;
	}

	@Override
	public boolean updatePrice(UpdatePriceDto dto) {
		Product product = productRepository.findById(dto.getProductId()).get();
		if (product == null) {
			return false;
		}

		product.setActualPrice(dto.getOriginalPrice());
		product.setPrice(dto.getNewPrice());

		productRepository.save(product);
		return true;
	}

	@Override
	public void addAdditionalImages(Long productId, AddAdditionalImagesDto imagesDto2) throws IOException {

		Product product = productRepository.findById(productId).get();
		if (product == null || productId == null) {
			return;
		}

		imageUploadService.uploadAdditionalImagesFromAdmin(product, imagesDto2);
	}

	@Override
	public void addPreviewImage(AddPreviewAndSmallImage dto) throws IOException {
		Product product = productRepository.findById(dto.getProductId()).get();
		if (product == null || dto.getProductId() == null) {
			return;
		}
		imageUploadService.uploadOnlyPreviewImageFromAdmin(product, dto);
	}

	@Cacheable(value = "suggestions", key = "#categoryId")
	@Override
	public List<ProductsSuggestionDto> getSuggestions(Long categoryId) {

		LOGGER.info("Fetching from database for category Id " + categoryId);
		Collection<Product> products = productRepository.findRandomProducts(categoryId);
		List<ProductsSuggestionDto> dtos = new ArrayList<>();
		products.forEach(p -> dtos.add(new ProductsSuggestionDto(p)));
		return dtos;
	}

}