package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.core.service.ProductImageService;
import com.aarria.retail.core.util.AppProperties;
import com.aarria.retail.core.util.Application;
import com.aarria.retail.core.util.FileUploadUtil;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.ProductImageRepository;
import com.aarria.retail.web.dto.response.ProductDetailsImageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	private Logger LOGGER = LogManager.getLogger(ProductImageServiceImpl.class);

	@Autowired
	private ProductImageRepository repository;

	@Autowired
	private AppProperties properties;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Override
	public Set<ProductImage> getAllProductColorsByProduct(Product product) {
		return repository.findByProduct(product);
	}

	@Override
	public ProductImage findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ProductDetailsImageDto> getColorDetails(Product product) {

		Set<ProductImage> colors = getAllProductColorsByProduct(product);

		if (colors == null || colors.isEmpty()) {
			return null;
		}

		List<ProductDetailsImageDto> allColors = new ArrayList<>();

		for (ProductImage color : colors) {
			allColors.add(Util.createProductDetailImagesFromProductColor(color.getId(),
					Application.IMAGES_DOMAIN + color.getImage()));
		}

		return allColors;
	}

	@Override
	public void deleteImageByProductAdditionalImageId(Long productAdditionalImageId) throws Exception {

		ProductImage productColor = repository.findById(productAdditionalImageId).get();

		String filePathToBeDeleted = properties.getStaticFilesRootPath() + File.separator + productColor.getImage();

		File file = new File(filePathToBeDeleted);

		Path countDirectory = Paths.get(file.getParentFile().getAbsolutePath());

		if (Files.exists(countDirectory)) {
			Files.walk(countDirectory).sorted(Comparator.reverseOrder()).map(Path::toFile).peek(LOGGER::info)
					.forEach(File::delete);
		}

		LOGGER.info("Deleting directory " + file.getParentFile().getAbsolutePath());

		repository.delete(productColor);

		fileUploadUtil.deleteDirectory(file.getParentFile().getAbsolutePath());

		fileUploadUtil.copyAllProductImages(productColor.getProduct().getId());
	}

	@Override
	public ProductImage saveProductImage(ProductImage pc) {
		return repository.save(pc);
	}

}
