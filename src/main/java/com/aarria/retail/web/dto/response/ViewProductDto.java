package com.aarria.retail.web.dto.response;

import java.util.Set;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.core.util.Util;

public class ViewProductDto {

	private Long id;

	private String pid;

	private String name;

	private String image;

	private Double weight;

	private String additionalImages;

	public ViewProductDto(Long id, String pid, String name, String image, Double weight, String additionalImages) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.image = image;
		this.weight = weight;
		this.additionalImages = additionalImages;
	}

	public static ViewProductDto createFromProduct(Product p) {
		String imageLocation = Util.generateImageLocation(p.getListingImage());
		return new ViewProductDto(p.getId(), p.getPid(), p.getName(), imageLocation, p.getWeight(),
				getImages(p.getColorImages()));
	}

	private static String getImages(Set<ProductImage> imgs) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (ProductImage p : imgs) {
			sb.append(Util.generateImageLocation(p.getImage()));

			if (++i != imgs.size()) {
				sb.append(",");
			}
		}

		String s = sb.toString();

		s = s.replace("small", "original");
		return s;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getAdditionalImages() {
		return additionalImages;
	}

	public void setAdditionalImages(String additionalImages) {
		this.additionalImages = additionalImages;
	}

}
