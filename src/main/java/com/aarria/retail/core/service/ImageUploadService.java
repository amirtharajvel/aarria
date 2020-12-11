package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.web.dto.request.AddAdditionalImagesDto;
import com.aarria.retail.web.dto.request.AddPreviewAndSmallImage;
import com.aarria.retail.web.dto.request.AddProductDto;

import java.io.IOException;

public interface ImageUploadService {

	void uploadAllProductImagesFromAddProducts(AddProductDto dto, Product product) throws IOException;

	void uploadAdditionalImagesFromAdmin(Product product, AddAdditionalImagesDto imagesDto2) throws IOException;

	void uploadOnlyPreviewImageFromAdmin(Product product, AddPreviewAndSmallImage AddPreviewImageDto) throws IOException;
}
