package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Pincode;
import com.aarria.retail.web.dto.response.PincodeCheckDto;
import org.springframework.web.multipart.MultipartFile;

public interface PincodeService {

	void uploadCsv(MultipartFile csvFile);

	Pincode findByPincode(String pincode);
	
	PincodeCheckDto checkPincodeAvailability(String pincode);
}
