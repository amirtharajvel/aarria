package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Attribute;

public class AttributeDto {

	private Long id;

	private String value;

	private String refiner;

	public AttributeDto() {
		super();
	}

	public AttributeDto(Attribute attribute) {
		this.setId(attribute.getId());
		this.setValue(attribute.getValue());
		this.setRefiner(attribute.getRefiner());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRefiner() {
		return refiner;
	}

	public void setRefiner(String refinerName) {
		this.refiner = refinerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AttributeDto other = (AttributeDto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}


}
