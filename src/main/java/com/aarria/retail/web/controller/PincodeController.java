package com.aarria.retail.web.controller;

import com.aarria.retail.core.service.PincodeService;
import com.aarria.retail.web.dto.request.PincodeUploadDto;
import com.aarria.retail.web.dto.response.PincodeCheckDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PincodeController {

	@Autowired
	private PincodeService pincodeService;

	@RequestMapping(path = "/checkPincodeAvailability", method = RequestMethod.GET)
	public @ResponseBody PincodeCheckDto checkPincodeAvailability(@RequestParam String pincode) {
		return pincodeService.checkPincodeAvailability(pincode);
	}

	@RequestMapping(value = "/pincodeupload", method = { RequestMethod.POST})
	public String uploadPincodeCsv(@ModelAttribute("model") PincodeUploadDto dto) {

		if (dto == null || dto.getCsvFile() == null) {
			return "unauthorized";
		}

		pincodeService.uploadCsv(dto.getCsvFile());

		return "pincodeupload";
	}
}
