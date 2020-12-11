package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.util.Enum.ImageType;
import org.springframework.web.multipart.MultipartFile;

public class AddPreviewAndSmallImage {

	private MultipartFile previewOrSmallImage;
	private Long productId;
	private Long imageId;
	private ImageType imageType;

	public MultipartFile getPreviewOrSmallImage() {
		return previewOrSmallImage;
	}

	public void setPreviewOrSmallImage(MultipartFile previewOrSmallImage) {
		this.previewOrSmallImage = previewOrSmallImage;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	
}
