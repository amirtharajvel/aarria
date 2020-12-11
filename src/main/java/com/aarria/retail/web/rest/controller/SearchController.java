package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.service.SearchService;
import com.aarria.retail.web.dto.response.SearchDto;
import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/getTerms", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getAllCategories(@RequestParam("query") String query) {

		List<SearchDto> dtos = searchService.query(query);

		if (CollectionUtils.isEmpty(dtos)) {
			dtos = new ArrayList<>();
			SearchDto noResults = new SearchDto(null, "No Results", "NORESULT", null);
			dtos.add(noResults);
		}

		return new Gson().toJson(dtos);
	}

	@RequestMapping(value = "/startIndexing")
	public @ResponseBody String startIndexing() {

		searchService.startIndexing();

		return "success";
	}
}
