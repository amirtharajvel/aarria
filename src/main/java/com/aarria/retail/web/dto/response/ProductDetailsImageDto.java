package com.aarria.retail.web.dto.response;

public class ProductDetailsImageDto {

	private Long id;

	private String small;
	private String preview;
	private String original;

	public ProductDetailsImageDto(Long id, String small, String preview, String original) {
		super();
		this.id = id;
		this.small = small;
		this.preview = preview;
		this.original = original;
	}

	public Long getId() {
		return id;
	}

	public String getSmall() {
		return small;
	}

	public String getPreview() {
		return preview;
	}

	public String getOriginal() {
		return original;
	}

}
