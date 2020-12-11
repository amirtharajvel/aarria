package com.aarria.retail.web.exceptionhandler;

import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.web.exception.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

	private static Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

	@Autowired
	private MessageService messageService;

	@ExceptionHandler(value = IllegalStateException.class)
	public String handleIllegalStateExceptions() {
		return "home";
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFoundExceptions(ResourceNotFoundException e, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("errors");
		view.setStatus(HttpStatus.NOT_FOUND);
		String errorMessage = e + " " + Util.retrieveStackTraceFromException(e);
		messageService.sendEmailToAdmin(errorMessage, "From Error Handler - Not found");
		return view;
	}

	@ExceptionHandler(value = Exception.class)
	public RedirectView handleAllExceptions(Exception e, HttpServletRequest request) {

		String errorMessage = e + " " + Util.retrieveStackTraceFromException(e);

		messageService.sendEmailToAdmin(errorMessage, "From Error Handler - Exception");

		RedirectView rw = new RedirectView();

		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		if (outputFlashMap != null) {
			outputFlashMap.put("errorMsg", errorMessage);
		}
		rw.setUrl("errors");
		return rw;
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ModelAndView handleParameterMissingExceptions(HttpServletRequest request,
			MissingServletRequestParameterException e) {

		String uri = new ServletServerHttpRequest(request).getURI().toString();
		ModelAndView view = new ModelAndView("errors");
		String errorMessage = uri + "\n" + e.getMessage() + Util.retrieveStackTraceFromException(e);
		view.addObject("errorMsg", errorMessage);

		messageService.sendEmailToAdmin(errorMessage,
				request.getRemoteAddr() + "-" + uri + "-MissingServletRequestParameterException-");

		LOGGER.error("Caught the exception in MissingServletRequestParameterException error handler for uri " + uri, e);

		return view;
	}
}
