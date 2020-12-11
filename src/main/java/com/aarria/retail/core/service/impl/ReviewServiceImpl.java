package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.Review;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.ProductService;
import com.aarria.retail.core.service.ReviewService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.persistence.repository.ReviewRepository;
import com.aarria.retail.web.dto.request.SubmitReviewDto;
import com.aarria.retail.web.dto.response.IndividualReviewDto;
import com.aarria.retail.web.dto.response.ReviewDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Override
	public ReviewDto getReviewsForProduct(Long productId) {

		List<Review> reviews = repository.findByProduct(productId);
		ReviewDto reviewDto = new ReviewDto();
		Double average = 0.0d;

		if (CollectionUtils.isNotEmpty(reviews)) {
			List<IndividualReviewDto> reviewDtos = new ArrayList<>();
			for (Review r : reviews) {
				IndividualReviewDto dto = new IndividualReviewDto(r.getReview(), r.getName(), r.getScore(),
						r.getAddedDate().toString(), r.getTitle());
				reviewDtos.add(dto);

				if (average == null) {
					average = 0d;
				}

				if (r.getScore() != null) {
					average = average + r.getScore();
				}
			}

			reviewDto.setOverallRatingForProduct(average / reviews.size());
			reviewDto.setReviews(reviewDtos);
			return reviewDto;
		}

		return null;
	}

	@Override
	public boolean isValidProduct(String pid) {
		Product product = productService.findByProductPid(pid);
		if (product != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean saveReview(SubmitReviewDto dto, Long userId) {
		Product product = productService.findByProductPid(dto.getPid());
		User user = userService.findOne(userId);
		Review review = repository.save(dto.createReview(product, user));
		if (review != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isUserAlreadyReviewed(String pid, User user) {

		List<Review> reviews = repository.findByProductAndUser(pid, user);

		if (reviews == null || CollectionUtils.isEmpty(reviews)) {
			return false;
		}

		return true;
	}

}
