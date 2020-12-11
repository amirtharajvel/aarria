package com.aarria.retail.core.util;

import com.aarria.retail.core.util.Enum.ImageType;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Resizer {

	public static void resizeImage(String inputPath, String outputPath, ImageType imageType) throws IOException {

		System.out.println("Resizing image for inputPath = " + inputPath + ", outputPath = " + outputPath
				+ " for image type = " + imageType);

		BufferedImage buf = ImageIO.read(new File(inputPath));
		AspectRatio ratio = getSize(imageType, buf);
		resizeUsingJavaAlgo(inputPath, new File(outputPath), ratio.getScaledWidth(), ratio.getScaledHeight());
	}

	private static AspectRatio getSize(ImageType imageType, BufferedImage buf) {
		Integer scaledHeight = Application.SIZES.get(imageType);
		Integer scaledWidth = imageType.name().equals(ImageType.THUMBNAIL.name()) ? 360 : scaledHeight;

		return new AspectRatio(scaledHeight, scaledWidth, buf.getHeight(), buf.getWidth());
	}

	public static boolean resizeUsingJavaAlgo(String source, File dest, int width, int height) throws IOException {
		BufferedImage sourceImage = ImageIO.read(new FileInputStream(source));
		double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
		if (width < 1) {
			width = (int) (height * ratio + 0.4);
		} else if (height < 1) {
			height = (int) (width / ratio + 0.4);
		}

		Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedScaled.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(scaled, 0, 0, width, height, null);
		dest.createNewFile();
		writeJpeg(bufferedScaled, dest.getCanonicalPath(), 1.0f, FilenameUtils.getExtension(source));
		return true;
	}

	/**
	 * Write a JPEG file setting the compression quality.
	 *
	 * @param image
	 *            a BufferedImage to be saved
	 * @param destFile
	 *            destination file (absolute or relative path)
	 * @param quality
	 *            a float between 0 and 1, where 1 means uncompressed.
	 * @throws IOException
	 *             in case of problems writing the file
	 */
	private static void writeJpeg(BufferedImage image, String destFile, float quality, String format)
			throws IOException {
		ImageWriter writer = null;
		FileImageOutputStream output = null;
		try {
			writer = ImageIO.getImageWritersByFormatName(format).next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(quality);
			output = new FileImageOutputStream(new File(destFile));
			writer.setOutput(output);
			IIOImage iioImage = new IIOImage(image, null, null);
			writer.write(null, iioImage, param);
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.dispose();
			}
			if (output != null) {
				output.close();
			}
		}
	}
}