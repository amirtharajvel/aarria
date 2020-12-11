package com.aarria.retail.web.rest.controller;

import com.aarria.retail.core.domain.Category;
import com.aarria.retail.core.service.CategoryService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.web.dto.response.CategoryDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/getCategory", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getCategoryById(@RequestBody Long id) {

		long start = System.currentTimeMillis();

		Category category = categoryService.findActiveCategories(id);

		CategoryDto categoriesDto = null;
		if (category != null) {
			categoriesDto = Util.createChildrenCategory(category);
			categoriesDto.setBreadcrumps(Util.getParents(category));
		}

		Gson gson = new Gson();
		String json = gson.toJson(categoriesDto);
		System.out.println("end time " + (System.currentTimeMillis() - start));
		return json;
	}

}
