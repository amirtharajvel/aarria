package com.aarria.retail.core.util;

import com.aarria.retail.core.exception.UnexpectedException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {

	public static void resizeImage(String source, String destination, int height, int width)
			throws FileNotFoundException, IOException {

		if (source == null) {
			throw new UnexpectedException("source is null when resizing images");
		}

		if (destination == null) {
			throw new UnexpectedException("destination is null when resizing images");
		}

		if (!destination.contains(".")) {
			throw new UnexpectedException("destination does not contain extension when resizing images");
		}

		BufferedImage bim = ImageIO.read(new FileInputStream(source));

		Image resizedImg = bim.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		BufferedImage rBimg = new BufferedImage(width, height, bim.getType());

		Graphics2D g = rBimg.createGraphics();

		g.drawImage(resizedImg, 0, 0, null);

		g.dispose();

		ImageIO.write(rBimg, destination.substring(destination.indexOf(".") + 1), new FileOutputStream(destination));

	}
}
