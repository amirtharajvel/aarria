package com.aarria.retail.core.util;

public class AspectRatio {

	private int scaledWidth;
	private int scaledHeight;
	int height;
	int width;

	public AspectRatio(int scaledHeight, int scaledWidth, int imageHeight, int imageWidth) {
		this.scaledHeight = scaledHeight;
		this.scaledWidth = scaledWidth;
		height = imageHeight;
		width = imageWidth;

		calculateAspectRatio();
	}

	private void calculateAspectRatio() {
		if (width > height) {
			this.scaledHeight = (height * scaledWidth) / width;
		} else {
			this.scaledWidth = (width * scaledHeight) / height;
		}
	}

	public int getScaledHeight() {
		return this.scaledHeight;
	}

	public int getScaledWidth() {
		return this.scaledWidth;
	}

	public static void main(String[] args) {
		AspectRatio r = new AspectRatio(340, 350, 100, 50);
		System.out.println("height = " + r.getScaledHeight() + ", width = " + r.getScaledWidth());
	}
}
