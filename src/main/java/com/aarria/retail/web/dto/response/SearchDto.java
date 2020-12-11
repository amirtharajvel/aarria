package com.aarria.retail.web.dto.response;

public class SearchDto {

	private Long id;

	private String name;

	private String type;

	private String pid;

	public SearchDto(Long id, String name, String type, String pid) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SearchDto [id=" + id + ", name=" + name + ", type=" + type + ", pid=" + pid + "]";
	}
	
}
