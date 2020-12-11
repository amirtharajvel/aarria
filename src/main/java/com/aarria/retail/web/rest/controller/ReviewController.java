package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.ReviewService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.SubmitReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ValidationService validationService;

	@RequestMapping("/getReviews")
	public String getReviewsForProduct(@RequestParam("productId") Long productId) {

		return null;

	}

	@RequestMapping("/writereview")
	public ModelAndView writeReview(@RequestParam("pid") String pid, HttpSession session) {

		ModelAndView view = new ModelAndView("writereview");

		if (session.getAttribute("userId") == null) {
			view.setViewName("redirect:" + View.LOGIN);
			return view;
		}

		if (!reviewService.isValidProduct(pid)) {
			view.setViewName("redirect:/");
			return view;
		}

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName("redirect:/");
			return view;
		}

		if (reviewService.isUserAlreadyReviewed(pid, user)) {
			view.addObject("isUserAlreadyReviewed", true);
			return view;
		}

		view.addObject("pid", pid);

		return view;
	}

	@RequestMapping(value = "/submitreview", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView submitReview(HttpSession session, SubmitReviewDto dto) {

		ModelAndView view = new ModelAndView("writereview");

		if (session.getAttribute("userId") == null || dto == null || dto.getReview() == null || dto.getPid() == null) {
			view.setViewName("redirect:" + View.LOGIN);
			return view;
		}

		boolean isSaved = reviewService.saveReview(dto, Long.valueOf(session.getAttribute("userId").toString()));
		view.addObject("reviewSaved", isSaved);

		return view;
	}

}
