package com.aarria.retail.web.dto.request;

public class SizewiseStockDto {

	private String key;

	private Integer value;

	public SizewiseStockDto(String key, Integer value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
