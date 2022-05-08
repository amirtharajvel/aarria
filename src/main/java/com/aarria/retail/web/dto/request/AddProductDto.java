package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.Category;
import com.aarria.retail.core.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.util.*;

public class AddProductDto {

	@Size(max = 1000)
	private String name;

	@Digits(message = "Only 7 integers and 2 fractions allowed", fraction = 2, integer = 7)
	private Double originalPrice;

	@Digits(message = "Only 7 integers 2 fractions allowed", fraction = 2, integer = 7)
	private Double actualPrice;

	@Digits(message = "Only 7 integers 2 fractions allowed", fraction = 2, integer = 7)
	private Double price;

	@Digits(message = "Only 7 integers 2 fractions allowed", fraction = 2, integer = 7)
	private Double offerPrice;

	private List<String> couponCodes = new ArrayList<>();

	private LinkedHashMap<Long, String> rendercategories = new LinkedHashMap<Long, String>();

	private Set<Long> categories = new HashSet<>();

	private List<String> colors = new ArrayList<>();

	private MultipartFile listingThumb;

	private Boolean isShowDiscount;

	private MultipartFile[] additionalImages;

	private String measureMeans;

	private String newMeasurementUnit;

	private Integer MOQ;

	private String measureUnit;

	private List<String> sizes;

	private List<DescriptionDto> description = new ArrayList<>();

	private List<SearchableAttributeDto> searchableAttributes = new ArrayList<>();

	private Double weight;

	private String video;

	private List<SizewiseStockDto> sizewiseStock = new ArrayList<>();

	private Double profitToBeAdded;

	private Double igstRate = 0.05;

	private String igstCode;

	private String offerText;

	private Integer offerQuantity;

	private String offerCode;

	private String deliveryCharge = "FREE SHIPPING";

	private List<String> cdnDirectUrls = new ArrayList<>(16);

	private List<DescriptionDto> shippingAndReturns = new ArrayList<>();

	private String catalogue;

	public AddProductDto() {

		addDefaultShippingAndReturns();

		addDefaultDescription();

		addDefaultSearcheableAttributes();

		for (int i = 0; i < 25; i++) {
			DescriptionDto desc = new DescriptionDto("", "");
			description.add(desc);

			DescriptionDto shipAndReturn = new DescriptionDto("", "");
			shippingAndReturns.add(shipAndReturn);

			SearchableAttributeDto brand = new SearchableAttributeDto("", "");
			searchableAttributes.add(brand);

			cdnDirectUrls.add("Hello");
		}

		for (int i = 0; i < 20; i++) {
			SizewiseStockDto dto = new SizewiseStockDto("", 0);
			sizewiseStock.add(dto);
		}
	}

	private void addDefaultSearcheableAttributes() {

		SearchableAttributeDto type = new SearchableAttributeDto("Type", "");
		searchableAttributes.add(type);

		SearchableAttributeDto material = new SearchableAttributeDto("Material", "");
		searchableAttributes.add(material);

	}

	private void addDefaultDescription() {

		DescriptionDto wash = new DescriptionDto("Wash", "Dry Wash Only");
		description.add(wash);

		DescriptionDto wash1 = new DescriptionDto("Sleeve", "");
		description.add(wash1);

		DescriptionDto desc = new DescriptionDto("Fabric", "");
		description.add(desc);

		DescriptionDto desc1 = new DescriptionDto("Transparency", "");
		description.add(desc1);

	}

	private void addDefaultShippingAndReturns() {
		DescriptionDto freeShipping = new DescriptionDto("Shipping", "Free Shipping");
		shippingAndReturns.add(freeShipping);

		DescriptionDto returns = new DescriptionDto("Return", "7 Days Return");
		shippingAndReturns.add(returns);

		DescriptionDto deliveryTime = new DescriptionDto("Delivery time",
				"Usually takes 3-4 days. Might vary based on the location.");
		shippingAndReturns.add(deliveryTime);
	}

	public Product getProduct() {
		Product product = new Product();
		product.setActualPrice(actualPrice);
		product.setOriginalPrice(originalPrice);
		product.setPrice(price);
		product.setCategories(saveCategories());
		product.setName(name);
		product.setIsWholesale(isShowDiscount);
		product.setMOQ(MOQ);
		product.setMeasureMeans(measureMeans);
		product.setAddedDate(new Date());
		product.setWeight(weight);
		product.setVideo(video);
		product.setProfitToBeAdded(profitToBeAdded);
		product.setIgstRate(igstRate);
		product.setIgstCode(igstCode);
		product.setOfferText(offerText);
		product.setOfferQuantity(offerQuantity);
		product.setDeliveryCharge(deliveryCharge);
		product.setOfferPrice(offerPrice);
		product.setCatalogue(catalogue);
		product.setIsShowDiscount(isShowDiscount);

		offerCode = offerCode == null ? null : offerCode.trim();
		product.setOfferCode(offerCode);

		computeMeasureUnit(product);

		return product;
	}

	public static void main(String[] args) {
		double actualPrice = 4000;
		double sellingPrice = 4001;

		double discount = 100 - (sellingPrice / actualPrice) * 100;
		System.out.println("discount is " + discount + ", round" + Math.round(discount));
	}

	private void computeMeasureUnit(Product product) {
		if (newMeasurementUnit != null && newMeasurementUnit.trim().length() > 0) {
			product.setMeasureUnit(newMeasurementUnit);
		} else {
			product.setMeasureUnit(measureUnit);
		}
	}

	public Set<Category> saveCategories() {
		Set<Category> catgrs = new HashSet<>();
		if (this.categories != null && !this.categories.isEmpty()) {
			for (Long l : categories) {
				Category c = new Category();
				c.setId(l);

				catgrs.add(c);
			}
		}

		return catgrs;
	}

	public LinkedHashMap<Long, String> createCategoriesForView(List<Category> cats) {
		LinkedHashMap<Long, String> categories = new LinkedHashMap<Long, String>();
		if (cats != null && !cats.isEmpty()) {
			for (Category category : cats) {

				String topCategoryName = "";

				if (category.getParentCategory() != null && category.getParentCategory().getParentCategory() != null) {
					topCategoryName = " - " + " [" + category.getParentCategory().getParentCategory().getName() + "]";
				}

				String parentCategoryName = category.getParentCategory() != null
						&& !category.getParentCategory().getName().trim().isEmpty()
								? " - " + category.getParentCategory().getName()
								: "";

				categories.put(category.getId(), category.getName() + parentCategoryName + topCategoryName);
			}
		}

		return categories;
	}

	public void resetValues() {
		this.setName(this.getName());
		this.setActualPrice(this.getActualPrice());
		this.setOriginalPrice(this.getOriginalPrice());
		this.setPrice(this.getPrice());
		this.setRendercategories(this.getRendercategories());
		this.setIsShowDiscount(this.getIsShowDiscount());
		this.setListingThumb(this.getListingThumb());
		this.setCategories(this.getCategories());
		this.setWeight(this.getWeight());
		this.setVideo(this.getVideo());
		this.setSizewiseStock(this.getSizewiseStock());

	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LinkedHashMap<Long, String> getRendercategories() {
		return rendercategories;
	}

	public void setRendercategories(LinkedHashMap<Long, String> rendercategories) {
		this.rendercategories = rendercategories;
	}
	
	public Set<Long> getCategories() {
		return categories;
	}

	public void setCategories(Set<Long> categories) {
		this.categories = categories;
	}

	public MultipartFile getListingThumb() {
		return listingThumb;
	}

	public void setListingThumb(MultipartFile listingThumb) {
		this.listingThumb = listingThumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsShowDiscount() {
		return isShowDiscount;
	}

	public void setIsShowDiscount(Boolean isShowDiscount) {
		this.isShowDiscount = isShowDiscount;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public MultipartFile[] getAdditionalImages() {
		return additionalImages;
	}

	public void setAdditionalImages(MultipartFile[] additionalImages) {
		this.additionalImages = additionalImages;
	}

	public String getMeasureMeans() {
		return measureMeans;
	}

	public void setMeasureMeans(String measureMeans) {
		this.measureMeans = measureMeans;
	}

	public String getNewMeasurementUnit() {
		return newMeasurementUnit;
	}

	public void setNewMeasurementUnit(String newMeasurementUnit) {
		this.newMeasurementUnit = newMeasurementUnit;
	}

	public Integer getMOQ() {
		return MOQ;
	}

	public void setMOQ(Integer mOQ) {
		MOQ = mOQ;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public List<String> getSizes() {
		return sizes;
	}

	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

	public List<DescriptionDto> getDescription() {
		return description;
	}

	public void setDescription(List<DescriptionDto> description) {
		this.description = description;
	}

	public List<SearchableAttributeDto> getSearchableAttributes() {
		return searchableAttributes;
	}

	public void setSearchableAttributes(List<SearchableAttributeDto> searchableAttributes) {
		this.searchableAttributes = searchableAttributes;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<SizewiseStockDto> getSizewiseStock() {
		return sizewiseStock;
	}

	public void setSizewiseStock(List<SizewiseStockDto> sizewiseStock) {
		this.sizewiseStock = sizewiseStock;
	}

	public Double getProfitToBeAdded() {
		return profitToBeAdded;
	}

	public void setProfitToBeAdded(Double profitToBeAdded) {
		this.profitToBeAdded = profitToBeAdded;
	}

	public Double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(Double igstRate) {
		this.igstRate = igstRate;
	}

	public String getIgstCode() {
		return igstCode;
	}

	public void setIgstCode(String igstCode) {
		this.igstCode = igstCode;
	}

	public String getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(String deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public List<DescriptionDto> getShippingAndReturns() {
		return shippingAndReturns;
	}

	public void setShippingAndReturns(List<DescriptionDto> shippingAndReturns) {
		this.shippingAndReturns = shippingAndReturns;
	}

	public String getOfferText() {
		return offerText;
	}

	public void setOfferText(String offerText) {
		this.offerText = offerText;
	}

	public Integer getOfferQuantity() {
		return offerQuantity;
	}

	public void setOfferQuantity(Integer offerQuantity) {
		this.offerQuantity = offerQuantity;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

	public List<String> getCdnDirectUrls() {
		return cdnDirectUrls;
	}

	public void setCdnDirectUrls(List<String> cdnDirectUrls) {
		this.cdnDirectUrls = cdnDirectUrls;
	}

	@Override
	public String toString() {
		return "AddProductDto [name=" + name + ", originalPrice=" + originalPrice + ", actualPrice=" + actualPrice
				+ ", price=" + price + ", rendercategories=" + rendercategories + ", categories=" + categories
				+ ", colors=" + colors + ", listingThumb=" + listingThumb + ", isShowDiscount=" + isShowDiscount
				+ ", additionalImages=" + additionalImages + ", measureMeans=" + measureMeans + ", newMeasurementUnit="
				+ newMeasurementUnit + ", MOQ=" + MOQ + ", measureUnit=" + measureUnit + ", sizes=" + sizes
				+ ", description=" + description + ", searchableAttributes=" + searchableAttributes + ", weight="
				+ weight + ", video=" + video + ", sizewiseStock=" + sizewiseStock + ", profitToBeAdded="
				+ profitToBeAdded + ", igstRate=" + igstRate + ", igstCode=" + igstCode + ", offerText=" + offerText
				+ ", offerQuantity=" + offerQuantity + ", offerPrice=" + offerPrice + ", offerCode=" + offerCode
				+ ", deliveryCharge=" + deliveryCharge + ", shippingAndReturns=" + shippingAndReturns + ", catalogue="
				+ catalogue + "]";
	}

}
