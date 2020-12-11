package com.aarria.retail.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aarria.retail.core.service.HomeService;
import com.aarria.retail.core.util.AppProperties;
import com.aarria.retail.core.util.Application;
import com.aarria.retail.web.dto.request.SubmitCreateHomePageDto;
import com.aarria.retail.web.dto.response.HomePageProductDto;

@Controller
public class CreateHomePageController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private AppProperties appProperties;

	@RequestMapping(value = "/createhomepage", method = RequestMethod.GET)
	public ModelAndView createHomePage() {
		ModelAndView view = new ModelAndView("createhomepage");

		view.addObject("allProductsOrderByAddedDateDesc", homeService.allProductsOrderByAddedDateDesc());
		view.addObject("popularProducts", homeService.getPopularProducts());
		view.addObject("bestOffers", homeService.getBestOffersProducts());

		return view;
	}

	@RequestMapping(value = "/createhomepagehtml", method = RequestMethod.POST)
	public ModelAndView createHomePageHtml(SubmitCreateHomePageDto dto) throws IOException {
		System.out.println("submit create home page " + dto);
		ModelAndView view = new ModelAndView("createhomepage");

		createHtml(dto);
		return view;
	}

	private void createHtml(SubmitCreateHomePageDto homePageDto) throws IOException {
		String homePagePath = "/Users/hasikasri/Documents/workspace/fallsbuy/src/main/webapp/WEB-INF/view/dynamicHome.jsp";
		File htmlTemplateFile = new File(homePagePath);
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String cdnResources = appProperties.cdnResources;

		htmlString = createNewArrivals(homePageDto, htmlString);

		htmlString = htmlString.replace("$cdnResources", cdnResources);
		File newHtmlFile = new File(
				"/Users/hasikasri/Documents/workspace/fallsbuy/src/main/webapp/WEB-INF/view/home.jsp");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
	}

	private String createNewArrivals(SubmitCreateHomePageDto homePageDto, String htmlString) {
		List<HomePageProductDto> newArrivals = homeService.getSelectedProducts(homePageDto.getNewArrivals());
		for (int i = 0; i < newArrivals.size(); i++) {
			htmlString = htmlString.replace("$newArrivalsImage" + i,
					Application.IMAGES_DOMAIN + newArrivals.get(i).getImage());
			htmlString = htmlString.replace("$newArrivalsPrice" + i, newArrivals.get(i).getPrice() + "");
			htmlString = htmlString.replace("$newArrivalsActualPrice" + i, newArrivals.get(i).getActualPrice() + "");
			htmlString = htmlString.replace("$newArrivalsOffer" + i, newArrivals.get(i).getDiscount() + "");
			htmlString = htmlString.replace("$newArrivalsName" + i, newArrivals.get(i).getName());
		}
		return htmlString;
	}

}
