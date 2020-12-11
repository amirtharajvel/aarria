package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.response.PrintInvoiceDto;
import com.aarria.retail.web.dto.response.ViewInvoiceDto;

public interface InvoiceService {

	PrintInvoiceDto generateInvoiceForTransport(String orderId, User user);

	ViewInvoiceDto generateInvoiceForUser(String orderId, User user);

}
