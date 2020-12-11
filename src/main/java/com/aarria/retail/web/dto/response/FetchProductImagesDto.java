package com.aarria.retail.web.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.util.Util;

public class FetchProductImagesDto {

	private Long productId;

	List<ProductDetailsImageDto> images = new ArrayList<>();

	public FetchProductImagesDto(Product p, List<ProductDetailsImageDto> colors) {
		this.productId = p.getId();

		if (colors != null && !colors.isEmpty()) {
			colors.forEach(i -> {

				ProductDetailsImageDto dto = Util.createProductDetailImages(i);
				images.add(dto);
			});
		}
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<ProductDetailsImageDto> getImages() {
		return images;
	}

	public void setImages(List<ProductDetailsImageDto> images) {
		this.images = images;
	}

}
