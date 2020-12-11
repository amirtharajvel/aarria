package com.aarria.retail.web.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class AddAdditionalImagesDto {

	private MultipartFile[] additionalImages;
	private Long productId;

	public MultipartFile[] getAdditionalImages() {
		return additionalImages;
	}

	public void setAdditionalImages(MultipartFile[] additionalImages) {
		this.additionalImages = additionalImages;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
