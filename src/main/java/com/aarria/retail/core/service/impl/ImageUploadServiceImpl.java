package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.core.service.ImageUploadService;
import com.aarria.retail.core.service.ProductImageService;
import com.aarria.retail.core.util.*;
import com.aarria.retail.core.util.Enum.ImageType;
import com.aarria.retail.web.dto.request.AddAdditionalImagesDto;
import com.aarria.retail.web.dto.request.AddPreviewAndSmallImage;
import com.aarria.retail.web.dto.request.AddProductDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

	private Logger LOGGER = LogManager.getLogger(ImageUploadServiceImpl.class);

	@Autowired
	private ProductImageService productImageService;

	@Autowired
	private AppProperties properties;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Override
	public void uploadAdditionalImagesFromAdmin(Product product, AddAdditionalImagesDto dto) throws IOException {
		createAndUploadDetailsImages(dto.getAdditionalImages(), product);
		fileUploadUtil.copyAllProductImages(product.getId());
	}

	@Override
	public void uploadAllProductImagesFromAddProducts(AddProductDto dto, Product product) throws IOException {
		createAndUploadThumbnail(dto.getListingThumb(), product);
		createAndUploadDetailsImages(dto.getAdditionalImages(), product);
		fileUploadUtil.copyAllProductImages(product.getId());
	}

	private void createAndUploadDetailsImages(MultipartFile[] images, Product product) throws IOException {
		try {

			int count = 0;
			for (MultipartFile multiPartFile : images) {

				if (!Util.isValidImageFormat(multiPartFile.getOriginalFilename())) {
					continue;
				}

				count = getCountOfAdditionalImage(product, count);

				++count;

				String uploadLocation = getUploadLocation(product, count);

				// original image
				String originalImageFullPath = uploadOriginalImage(multiPartFile, uploadLocation);

				// preview image
				uploadPreviewImage(multiPartFile, uploadLocation, originalImageFullPath);

				// small image
				String smallImage = uploadSmallImage(multiPartFile, uploadLocation, originalImageFullPath);

				// we store only the small image path in database. we extract the original and
				// preview
				// image path by replacing the location while retrieving
				storeImagePathInDb(product, count, smallImage);

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(Util.retrieveStackTraceFromException(e));
			throw new IOException(Util.retrieveStackTraceFromException(e));

		}
	}

	private int getCountOfAdditionalImage(Product product, int count) throws Exception {
		Set<ProductImage> allColors = productImageService.getAllProductColorsByProduct(product);

		if (allColors != null && !allColors.isEmpty() && allColors.size() > 0) {
			count = allColors.size();
		}

		if (count == 0) {
			String path = properties.getStaticFilesRootPath() + File.separator + product.getId();

			Path countDirectory = Paths.get(path);
			if (Files.exists(countDirectory)) {
				Files.walk(countDirectory).sorted(Comparator.reverseOrder()).map(Path::toFile).peek(LOGGER::info)
						.filter(file -> !file.getName().contains("thumbnail")).forEach(File::delete);
			}

		} else {
			count = getMaxCountInFolder(product.getId());
		}

		return count;
	}

	private int getMaxCountInFolder(Long productId) {
		File file = new File(properties.getStaticFilesRootPath() + File.separator + productId);
		File[] listOfImageFolderForProduct = file.listFiles();
		if (listOfImageFolderForProduct != null && listOfImageFolderForProduct.length > 0) {
			List<Integer> numbers = new ArrayList<>();
			for (File f : listOfImageFolderForProduct) {

				if (f.getAbsolutePath().contains(".")) {
					LOGGER.info("the file name has dot in it so continuing...");
					continue;
				}

				String path = f.getAbsolutePath();
				String countFolderName = path.substring(path.lastIndexOf(Application.SEPARATOR) + 1, path.length());

				try {
					numbers.add(Integer.parseInt(countFolderName));
				} catch (Exception e) {
					LOGGER.error("Unable to convert to integer while getting the max count " + e + " for "
							+ countFolderName);
				}

			}

			if (!numbers.isEmpty()) {
				return Collections.max(numbers);
			}

		}

		return 0;

	}

	public static void main(String[] args) {
		try {
			List<Integer> numbers = new ArrayList<>();
			Collections.max(numbers);
		} catch (Exception e) {
			System.out.println(e + Util.retrieveStackTraceFromException(e));
		}
	}

	private String getUploadLocation(Product product, int count) {
		return properties.getStaticFilesRootPath() + File.separator + product.getId() + File.separator + count
				+ File.separator;
	}

	private void uploadPreviewImage(MultipartFile multiPartFile, String uploadLocation, String originalImageFullPath)
			throws IOException {
		String previewImageName = uploadLocation
				+ Util.getThumbnailImageName(multiPartFile.getOriginalFilename(), ImageType.PREVIEW);

		Resizer.resizeImage(originalImageFullPath, previewImageName, ImageType.PREVIEW);
	}

	private String uploadSmallImage(MultipartFile multiPartFile, String uploadLocation, String originalImageFullPath)
			throws IOException {
		String smallImage = Util.getThumbnailImageName(multiPartFile.getOriginalFilename(), ImageType.SMALL);

		String smallImageFullPath = uploadLocation + smallImage;

		Resizer.resizeImage(originalImageFullPath, smallImageFullPath, ImageType.SMALL);
		return smallImage;
	}

	private String uploadOriginalImage(MultipartFile multiPartFile, String uploadLocation) throws IOException {
		String originalImageFullPath = uploadLocation
				+ Util.getThumbnailImageName(multiPartFile.getOriginalFilename(), ImageType.ORIGINAL);

		copyImageToLocalSystemFromBrowser(multiPartFile, uploadLocation, new File(originalImageFullPath));
		return originalImageFullPath;
	}

	private void storeImagePathInDb(Product product, int count, String thumbnailImage) {
		String pathToBeStoredInDb = product.getId() + File.separator + count + File.separator + thumbnailImage;

		ProductImage image = new ProductImage();
		image.setImage(pathToBeStoredInDb);
		image.setProduct(product);

		LOGGER.info("Storing image path in database " + pathToBeStoredInDb);

		image = productImageService.saveProductImage(image);
	}

	// thumbnail related files
	private void createAndUploadThumbnail(MultipartFile fileFromBrowser, Product product) throws IOException {

		String uploadLocation = properties.getStaticFilesRootPath() + File.separator + product.getId() + File.separator
				+ ImageType.THUMBNAIL.name().toLowerCase() + File.separator;

		String thumbnailImageName = uploadLocation
				+ Util.getThumbnailImageName(fileFromBrowser.getOriginalFilename(), ImageType.THUMBNAIL);

		copyImageToLocalSystemFromBrowser(fileFromBrowser, uploadLocation, new File(thumbnailImageName));

		product.setListingImage(product.getId() + File.separator + ImageType.THUMBNAIL.name().toLowerCase()
				+ File.separator + ImageType.THUMBNAIL.name().toLowerCase() + Application.DOT
				+ FilenameUtils.getExtension(fileFromBrowser.getOriginalFilename()));

	}

	private void copyImageToLocalSystemFromBrowser(MultipartFile multipartFile, String uploadLocation, File outputFile)
			throws IOException {

		LOGGER.info("Uploading image in location = " + uploadLocation + " with file " + outputFile.getAbsolutePath());

		Files.createDirectories(Paths.get(uploadLocation),
				PosixFilePermissions.asFileAttribute(Util.getDirectoryPermissions()));

		FileCopyUtils.copy(multipartFile.getBytes(), outputFile);

		outputFile.setExecutable(true);
		outputFile.setReadable(true);
		outputFile.setWritable(true);

	}

	@Override
	public void uploadOnlyPreviewImageFromAdmin(Product product, AddPreviewAndSmallImage dto) throws IOException {

		ProductImage image = productImageService.findById(dto.getImageId());

		if (image == null) {
			throw new RuntimeException("Unable to find the product image for the id " + dto.getImageId());
		}

		String previewImage = image.getImage().replace(ImageType.SMALL.name().toLowerCase().toString(),
				dto.getImageType().name().toLowerCase().toString());

		String destination = properties.getStaticFilesRootPath() + File.separator + previewImage;
		File previewImageUploadedLocation = new File(destination);
		if (!previewImageUploadedLocation.exists()) {
			previewImageUploadedLocation.getParentFile().mkdirs();
			previewImageUploadedLocation.getParentFile().setExecutable(true);
			previewImageUploadedLocation.getParentFile().setReadable(true);
			previewImageUploadedLocation.getParentFile().setWritable(true);
		}

		LOGGER.info("copying " + dto.getPreviewOrSmallImage().getOriginalFilename() + " to "
				+ previewImageUploadedLocation.getAbsolutePath() + " in local");
		FileCopyUtils.copy(dto.getPreviewOrSmallImage().getBytes(), previewImageUploadedLocation);

		fileUploadUtil.copyFile(previewImageUploadedLocation.getParentFile().getAbsolutePath(),
				previewImageUploadedLocation.getAbsolutePath(), destination);
		LOGGER.info(
				"Done copying " + previewImageUploadedLocation.getAbsolutePath() + " to " + destination + " in CDN ");

	}

}
