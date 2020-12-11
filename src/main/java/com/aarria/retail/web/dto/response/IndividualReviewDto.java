package com.aarria.retail.web.dto.response;

public class IndividualReviewDto {

	private String review;

	private String name;

	private Double score;

	private String addedDate;

	private String title;

	public IndividualReviewDto(String review, String name, Double score, String addedDate, String title) {
		super();
		this.review = review;
		this.name = name;
		this.score = score;
		this.addedDate = addedDate;
		this.title = title;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
}
