package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ViewCartDto {

	private List<CartItemsDto> items = new ArrayList<>();

	public ViewCartDto(Set<Cart> carts) {
		super();
		getProducts(carts);
	}

	public void getProducts(Set<Cart> carts) {

		if (carts != null) {
			for (Cart cart : carts) {
				CartItemsDto dto = new CartItemsDto();

				Product product = cart.getProduct();

				if (product != null) {

					dto.setName(product.getName());
					dto.setPid(product.getPid());
					dto.setProductId(product.getId());
					dto.setQuantity(cart.getQuantity());
					dto.setPrice(cart.getPrice());
					dto.setSize(cart.getSize());

					if (CollectionUtils.isNotEmpty(product.getProductStock())) {
						for (ProductStock ps : product.getProductStock()) {
							if (ps.getSize().equals(cart.getSize())) {
								dto.setStock(ps.getStock());
								break;
							}
						}
					}
				}

				if (cart.getColorImage() != null) {
					// dto.setImage(Application.IMAGES_DOMAIN +
					// cart.getColorImage()); TODO - uncomment and remove the
					// below line
					dto.setImage(cart.getColorImage());
				}

				this.items.add(dto);
			}
		}
	}

	public List<CartItemsDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemsDto> items) {
		this.items = items;
	}
}
