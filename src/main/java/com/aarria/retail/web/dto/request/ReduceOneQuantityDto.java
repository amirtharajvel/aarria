package com.aarria.retail.web.dto.request;

public class ReduceOneQuantityDto {

	private String pid;
	private String size;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ReduceOneQuantityDto [pid=" + pid + ", size=" + size + "]";
	}
}
