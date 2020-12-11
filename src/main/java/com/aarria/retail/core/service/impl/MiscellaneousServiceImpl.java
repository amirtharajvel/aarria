package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.core.service.MiscellaneousService;
import com.aarria.retail.core.service.ProductImageService;
import com.aarria.retail.core.service.ProductService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

@Service
public class MiscellaneousServiceImpl implements MiscellaneousService {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productColorsService;

	@Override
	public void images() {

		File fi = new File("/Users/hasikasri/Desktop/thumbnail-resize.png");
		File[] ff = fi.listFiles();

		try {
			// createThumbnail(fi, 400, "png",
			// "/private/var/www/html/resources/445/thumbnail/thumbnail-1.png");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (true) {
			// return;
		}

		String ROOT = "/Users/hasikasri/Desktop/experiment/resources/";

		Set<Product> products = productService.findAllProductsOrderByAddedDate();

		Map<Long, Integer> countMap = new HashMap<>();
		for (Product p : products) {

			Set<ProductImage> images = productColorsService.getAllProductColorsByProduct(p);

			if (images.size() > 1) {
				countMap.put(p.getId(), images.size());

			}

			// createThumbnailFolder(ROOT + "/" + p.getId() + "/thumbnail");

			// copyThumbnailFiles(ROOT + "/" + p.getListingImage(), ROOT + "/" + p.getId() +
			// "/thumbnail/");

			int count = 1;

			// p.setListingImage(p.getId() + "/thumbnail/thumbnail." +
			// FilenameUtils.getExtension(p.getListingImage()));

			// System.out.println("Printing dir name " + p.getListingImage());

			// productService.save(p);

			for (ProductImage pc : images) {
				try {

					// moveAdditionalFiles(ROOT + pc.getImage(), ROOT + p.getId() + "/" + count++);
					// main(resources, pc.getImage(), p.getId());
					
					pc.setImage(pc.getImage().replace("thumbnail", "small"));
					System.out.println("Bring " + pc.getImage());
					//productColorsService.saveProductColor(pc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (true) {
				continue;
			}

			File folder = new File(ROOT);
			File[] fol = folder.listFiles();

			for (File f : fol) {

				if (f.getName().equals(p.getId().longValue() + "")) {

					// File th = new File(ROOT + "/" + p.getId() + "/thumbnail/");
					//
					// File thumbnailFile = th.listFiles()[0];

					// p.setListingImage(p.getId() + "/thumbnail/thumbnail." +
					// FilenameUtils.getExtension(thumbnailFile.getName()));
					//
					// System.out.println("Printing dir name " + p.getListingImage());
					//
					// productService.save(p);

				}

			}

			File mainFolder = new File(ROOT + "/" + p.getId() + "/MAIN");

			File files = new File(ROOT + "/" + p.getId());

			File[] mainFiles = files.listFiles();
			if (mainFiles != null && mainFiles.length > 0) {
				for (File f : mainFiles) {
					if (f.isFile() && !f.isDirectory()) {
						System.out.println("Deleting.. " + f.getAbsolutePath());
						f.delete();
					}
				}
			}

			// from product service impl
			// Resizer.createThumbnail(resizeFile, 400,
			// FilenameUtils.getExtension(thumbnailImageName), uploadLocation +
			// Application.SEPARATOR + thumbnailImageName);

			File files1 = new File(ROOT + "/" + p.getId());

			File[] mainFiles1 = files1.listFiles();
			if (mainFiles1 != null && mainFiles1.length > 0) {
				for (File f : mainFiles1) {
					File[] last = f.listFiles();
					if (last != null) {

						for (File output : last) {

							if (output == null || output.getName() == null || output.getName().contains("original")
									|| output.getName().contains("preview")) {
								continue;
							}

							String extension = FilenameUtils.getExtension(output.getName());
							try {
								// createThumbnail(output, 80, extension,
								// f.getAbsolutePath() + "/thumbnail." + extension);

								// createPreviewAndOriginal(output, 400, extension,
								// f.getAbsolutePath() + "/preview." + extension);

								if (output.getAbsolutePath().contains("thumbnail/small")) {
									// output.renameTo(new File(f.getAbsolutePath() + "/thumbnail." + extension));
								}
								ProductImage pc = new ProductImage();
								String image = p.getId() + "/" + f.getName() + "/thumbnail." + extension;
								pc.setImage(image);
								pc.setProduct(p);

								// productColorsService.saveProductColor(pc);

							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
				}
			}

			try {
				FileUtils.deleteDirectory(mainFolder);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println(countMap);

	}

	private static void createThumbnailFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private static void copyThumbnailFiles(String path, String toPath) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("NOOOOOOOOOOOOOOOOOOOO " + file.getAbsolutePath());
		} else {
			System.out.println("YES exists " + file.getAbsolutePath());

			try {
				FileUtils.copyFile(file, new File(toPath + "thumbnail." + FilenameUtils.getExtension(file.getName())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void moveAdditionalFiles(String from, String to) {

		File dest = new File(to);
		if (!dest.exists()) {
			dest.mkdirs();
		}

		File source = new File(from);
		try {
			System.out.println("Copying..." + source.getAbsolutePath() + " to " + dest.getAbsolutePath());
			FileUtils.copyFile(source, new File(to + "/1." + FilenameUtils.getExtension(source.getName())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// private methods

	private static void fetchFiles(File dir, Consumer<File> fileConsumer) {

		if (dir.isDirectory()) {
			for (File file1 : dir.listFiles()) {
				fetchFiles(file1, fileConsumer);
			}
		} else {
			fileConsumer.accept(dir);
		}
	}

	private static void createPreviewAndOriginal(File file, int size, String format, String outputPath)
			throws IOException {

		if (format != null && (format.contains("png") || format.contains("jpg"))) {
			BufferedImage bimg = ImageIO.read(file);
			int width = bimg.getWidth();
			int height = bimg.getHeight();

			BufferedImage img = null;

			if (height > width) {
				img = Thumbnails.of(file.getAbsolutePath()).height(size).keepAspectRatio(true).outputFormat(format)
						.outputQuality(1.0).asBufferedImage();
			} else {
				img = Thumbnails.of(file.getAbsolutePath()).width(size).keepAspectRatio(true).outputFormat(format)
						.outputQuality(1.0).asBufferedImage();
			}

			if (file.getAbsolutePath().contains(".jpg") || file.getAbsolutePath().contains(".png")) {

				File thumbnail = new File(outputPath);

				ImageIO.write(img, format, thumbnail);

			}
		}

	}

	private static void createThumbnail(File file, int size, String format, String outputPath) throws IOException {

		if (format != null && (format.contains("png") || format.contains("jpg"))) {

			BufferedImage img = null;

			img = Thumbnails.of(file.getAbsolutePath()).height(size).keepAspectRatio(true).outputFormat(format)
					.outputQuality(1.0).asBufferedImage();

			if (file.getAbsolutePath().contains(".jpg") || file.getAbsolutePath().contains(".png")) {

				File thumbnail = new File(outputPath);

				ImageIO.write(img, format, thumbnail);

			}
		} else {
			System.out.println("NOOOOOOOOOO format is wrong.." + file.getAbsolutePath());
		}

	}
}
