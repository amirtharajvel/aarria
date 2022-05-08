//package com.aarria.retail.web.controller;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class ErrorController {
//
//	private static Logger LOGGER = LogManager.getLogger(ErrorController.class);
//
//	@RequestMapping(value = "errors", method = RequestMethod.GET)
//	public ModelAndView renderErrorPage(HttpServletRequest httpRequest, Model model) {
//
//		ModelAndView errorPage = new ModelAndView("error");
//		String errorMsg = "";
//		Integer httpErrorCode = getErrorCode(httpRequest);
//
//		if (httpErrorCode != null) {
//			if (httpErrorCode.equals(400)) {
//				errorMsg = "Http Error Code: 400. Bad Request";
//			}
//			if (httpErrorCode.equals(401)) {
//				errorMsg = "Sorry. You're not authorized to do this :)";
//			}
//			if (httpErrorCode.equals(404)) {
//				errorMsg = "The resource you're looking is not found.";
//			}
//			if (httpErrorCode.equals(500)) {
//				errorMsg = "Internal Server Error - We'll fix asap.";
//			} else {
//				errorMsg = "Something went wrong!";
//			}
//		} else {
//			Map<String, Object> m = model.asMap();
//			errorMsg = (String) m.get("errorMsg");
//		}
//
//		LOGGER.info("Http error code " + httpErrorCode + " request url is " + httpRequest.getRequestURL()
//				+ " and uri is " + httpRequest.getRequestURI() + "\nerror message is " + errorMsg);
//
//		if(httpErrorCode == null) {
//			errorMsg = ":(";
//		}
//
//		errorPage.addObject("errorMsg", errorMsg);
//		return errorPage;
//	}
//
//	private Integer getErrorCode(HttpServletRequest httpRequest) {
//		if (httpRequest != null) {
//			Object code = httpRequest.getAttribute("javax.servlet.error.status_code");
//			if (code != null) {
//				return (Integer) code;
//			}
//		}
//
//		return null;
//	}
//}