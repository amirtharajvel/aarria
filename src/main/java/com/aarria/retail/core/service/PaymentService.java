package com.aarria.retail.core.service;

import com.aarria.retail.web.dto.request.PaymentDto;

public interface PaymentService {

	PaymentDto parsePaymentResponse(String responseParametersFromPaymentGateway, Integer count);

}
