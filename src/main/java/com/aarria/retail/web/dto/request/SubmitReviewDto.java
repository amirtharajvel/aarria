package com.aarria.retail.web.dto.request;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.Review;
import com.aarria.retail.core.domain.User;

public class SubmitReviewDto {

	@NotBlank
	@Size(max = 100, min = 3)
	private String review;

	@Size(max = 100, min = 1)
	private String name;

	private Double score;

	private String addedDate;

	@Size(max = 100, min = 1)
	private String title;

	private String pid;

	public Review createReview(Product product, User user) {
		Review r = new Review();
		r.setAddedDate(new Date());
		r.setName(name);
		r.setProduct(product);
		r.setReview(review);
		r.setTitle(title);
		r.setUser(user);
		r.setScore(score);

		return r;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

}
