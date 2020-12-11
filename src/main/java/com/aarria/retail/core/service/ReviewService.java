package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.SubmitReviewDto;
import com.aarria.retail.web.dto.response.ReviewDto;

public interface ReviewService {

	public boolean isUserAlreadyReviewed(String pid, User user);

	public ReviewDto getReviewsForProduct(Long productId);

	public boolean isValidProduct(String pid);

	boolean saveReview(SubmitReviewDto dto, Long userId);
}
