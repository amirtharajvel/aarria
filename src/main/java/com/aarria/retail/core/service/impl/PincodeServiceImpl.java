package com.aarria.retail.core.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.aarria.retail.core.domain.Pincode;
import com.aarria.retail.core.service.PincodeService;
import com.aarria.retail.persistence.repository.PincodeRepository;
import com.aarria.retail.web.dto.response.PincodeCheckDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PincodeServiceImpl implements PincodeService {

	private static Logger LOGGER = LogManager.getLogger(PincodeServiceImpl.class);

	@Autowired
	private PincodeRepository pincodeRepository;

	@Override
	public PincodeCheckDto checkPincodeAvailability(String pincode) {
		String message = "Sorry. " + pincode + " is not in our delivery network. Please try any nearest pincode";
		String status = "fail";
		String expectedDeliveryDate = null;

		Pincode pincodeFromDatabase = findByPincode(pincode);
		if (pincodeFromDatabase != null) {
			message = "Delivery available to " + pincode + " - " + pincodeFromDatabase.getCirclename();
			status = "success";
			expectedDeliveryDate = "Within 24 hours";
		}

		return new PincodeCheckDto(expectedDeliveryDate, message, status);
	}

	@Override
	public Pincode findByPincode(String pincode) {
		return pincodeRepository.findByPincode(pincode);
	}

	@Override
	public void uploadCsv(MultipartFile csvFile) {

		CSVReader reader;

		try {
			reader = new CSVReader(new InputStreamReader(csvFile.getInputStream()), ',');

			// read line by line
			String[] record = null;

			while ((record = reader.readNext()) != null) {

				String officename = record[0];
				String pincode = record[1];
				String officeType = record[2];
				String DeliveryStatus = record[3];
				String divisionname = record[4];
				String regionname = record[5];
				String circlename = record[6];
				String Taluk = record[7];
				String Districtname = record[8];
				String statename = record[9];

				Pincode pin = new Pincode(officename, pincode, officeType, DeliveryStatus, divisionname, regionname,
						circlename, Taluk, Districtname, statename);
				// pincodes.add(pin);

				try {
					pincodeRepository.save(pin);
				} catch (Exception e) {
					System.out.println("Error saving " + pin);
				}

			}

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteDuplicatePincodes() {
		long startTime = System.currentTimeMillis();
		List<Pincode> pincodes = pincodeRepository.findAll();
		Set<String> pins = new HashSet<>();
		Set<Pincode> uniquePincodes = new HashSet<>();

		for (Pincode p : pincodes) {
			if (!pins.contains(p.getPincode())) {
				uniquePincodes.add(p);
				pins.add(p.getPincode());
			}
		}

		pincodeRepository.deleteAll();
		pincodeRepository.saveAll(uniquePincodes);

		LOGGER.info("total pincodes = " + pincodes.size());
		LOGGER.info("unique pincodes = " + uniquePincodes.size());
		LOGGER.info("Total time consumed = " + (System.currentTimeMillis() - startTime));

	}

}
