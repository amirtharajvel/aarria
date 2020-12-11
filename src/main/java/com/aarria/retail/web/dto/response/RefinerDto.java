package com.aarria.retail.web.dto.response;

import java.util.List;

public class RefinerDto {

	private String name;

	private List<AttributeDto> uniqueAttributes;

	public String getName() {
		return name;
	}

	public RefinerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RefinerDto(String name, List<AttributeDto> uniqueAttributes) {
		super();
		this.name = name;
		this.uniqueAttributes = uniqueAttributes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AttributeDto> getUniqueAttributes() {
		return uniqueAttributes;
	}

	public void setUniqueAttributes(List<AttributeDto> uniqueAttributes) {
		this.uniqueAttributes = uniqueAttributes;
	}

	// DO NOT CHANGE THIS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// DO NOT CHANGE THIS
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
		RefinerDto other = (RefinerDto) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		String s = " ";
		if (uniqueAttributes != null && !uniqueAttributes.isEmpty()) {
			for (AttributeDto a : uniqueAttributes) {
				s = s + a;
			}
		}
		return "RefinerDto [name=" + name + "]" + s;
	}
}
