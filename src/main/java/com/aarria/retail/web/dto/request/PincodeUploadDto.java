package com.aarria.retail.web.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PincodeUploadDto {

	private MultipartFile csvFile;

	public MultipartFile getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(MultipartFile csvFile) {
		this.csvFile = csvFile;
	}

}
