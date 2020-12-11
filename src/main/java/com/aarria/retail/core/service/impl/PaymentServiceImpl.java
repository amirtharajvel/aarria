package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.service.PaymentService;
import com.aarria.retail.web.dto.request.PaymentDto;
import com.ccavenue.security.AesCryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final String MERCHANT_PARAM3 = "merchant_param3";
	private static Logger LOGGER = LogManager.getLogger(PaymentServiceImpl.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaymentDto parsePaymentResponse(String responseParametersFromPaymentGateway, Integer paymentCount) {
		String workingKey = "2F19DC479C5558970B8B089BB066C2CE"; // 32 Bit
																// Alphanumeric
																// Working Key
																// should be
		// entered here so that data can be decrypted.

		AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(responseParametersFromPaymentGateway);

		List<String> l = new ArrayList<>();
		l.add(decResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Hashtable hs = new Hashtable();
		String pair = null, pname = null, pvalue = null;
		while (tokenizer.hasMoreTokens()) {
			pair = tokenizer.nextToken();
			if (pair != null) {
				StringTokenizer strTok = new StringTokenizer(pair, "=");
				pname = "";
				pvalue = "";
				if (strTok.hasMoreTokens()) {
					pname = strTok.nextToken();
					if (strTok.hasMoreTokens()) {
						pvalue = strTok.nextToken();
					}
					hs.put(pname, pvalue);
				}
			}
		}

		Enumeration enumeration = hs.keys();

		PaymentDto dto = new PaymentDto();

		while (enumeration.hasMoreElements()) {
			pname = "" + enumeration.nextElement();
			pvalue = "" + hs.get(pname);
			l.add(pname + " -> " + pvalue + ", ");

			if (pname.equals(MERCHANT_PARAM3) && Integer.valueOf(pvalue) != null) {

				LOGGER.info("Merchant param " + pvalue + " payment count " + paymentCount);
				// if the merchant param and session value is not equal then it's resubmission
				if (paymentCount != null && !paymentCount.equals(Integer.valueOf(pvalue))) {
					LOGGER.error("Resubmission occurred on payment gateway");
					throw new RuntimeException("Payment gateway accessed twice");
				}
			}

			try {
				PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(dto);
				myAccessor.setPropertyValue(pname, pvalue);
			} catch (Exception e) {
				dto.getUnIdentifiedPropertiesFromCCAvenue().add(e.toString());
			}
		}

		return dto;
	}

}
