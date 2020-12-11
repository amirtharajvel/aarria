package com.aarria.retail.web.dto.request;

import java.util.ArrayList;
import java.util.List;

public class SubmitCreateHomePageDto {

	private List<Long> newArrivals = new ArrayList<>();
	private List<Long> popular = new ArrayList<>();
	private List<Long> offers = new ArrayList<>();
	private List<Long> bestDeals = new ArrayList<>();

	public List<Long> getNewArrivals() {
		return newArrivals;
	}

	public void setNewArrivals(List<Long> newArrivals) {
		this.newArrivals = newArrivals;
	}

	public List<Long> getPopular() {
		return popular;
	}

	public void setPopular(List<Long> popular) {
		this.popular = popular;
	}

	public List<Long> getOffers() {
		return offers;
	}

	public void setOffers(List<Long> offers) {
		this.offers = offers;
	}

	public List<Long> getBestDeals() {
		return bestDeals;
	}

	public void setBestDeals(List<Long> bestDeals) {
		this.bestDeals = bestDeals;
	}
}
