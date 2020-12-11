package com.aarria.retail.core.service;

import com.aarria.retail.web.dto.response.SearchDto;

import java.util.List;

public interface SearchService {

	public void startIndexing();

	public List<SearchDto> query(String searchTerm);
}
