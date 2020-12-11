package com.aarria.retail.web.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ReviewDto {

	private Double overallRatingForProduct;
	private List<IndividualReviewDto> reviews = new ArrayList<>();
	
	public Double getOverallRatingForProduct() {
		return overallRatingForProduct;
	}
	public void setOverallRatingForProduct(Double overallRatingForProduct) {
		this.overallRatingForProduct = overallRatingForProduct;
	}
	public List<IndividualReviewDto> getReviews() {
		return reviews;
	}
	public void setReviews(List<IndividualReviewDto> reviews) {
		this.reviews = reviews;
	}
	
}
