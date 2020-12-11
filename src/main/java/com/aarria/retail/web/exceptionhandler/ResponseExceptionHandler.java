package com.aarria.retail.web.exceptionhandler;

import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Deprecated
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger LOGGER = LogManager.getLogger(ResponseExceptionHandler.class);

	@Autowired
	private MessageService messageService;

	@Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOGGER.error("Caught the exception in MissingServletRequestParameterException error handler ", e);
		ModelAndView view = new ModelAndView("error");
		String errorMessage = e.getMessage() + Util.retrieveStackTraceFromException(e);
		messageService.sendEmailToAdmin(errorMessage, "From MissingServletRequestParameterException Error Handler");
		view.addObject("errorMsg", errorMessage);
		LOGGER.info("In handleParameterMissingExceptions " + Util.retrieveStackTraceFromException(e));

		ResponseEntity<Object> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(final Exception ex, final WebRequest req) {
		return null;
	}

}