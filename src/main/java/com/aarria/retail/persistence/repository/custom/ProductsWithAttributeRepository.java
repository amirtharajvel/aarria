package com.aarria.retail.persistence.repository.custom;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.web.dto.response.ProductsWithAttributesDto;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductsWithAttributeRepository {

	@PersistenceContext
	private EntityManager em;

	public ProductsWithAttributesDto findProductsWithAttributes(String queryString, String countQueryString,
			int noOfProductsPerPage, int page) {

		System.out.println("queryString is " + queryString);

		Query countQuery = em.createQuery(countQueryString);

		@SuppressWarnings("unchecked")
		List<Object[]> list = countQuery.getResultList();

		Long count = 0l;
		Double minPrice = 0d;
		Double maxPrice = 0d;
		if (list != null) {
			Object[] obj = list.get(0);

			if (!ArrayUtils.isEmpty(obj)) {
				count = (Long) obj[0];
				minPrice = (Double) obj[1];
				maxPrice = (Double) obj[2];
			}
		}

		int firstResult = page * noOfProductsPerPage;

		TypedQuery<Product> query = em.createQuery(queryString, Product.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(noOfProductsPerPage);

		List<Product> products = query.getResultList();

		if (products == null) {
			return null;
		}

		return new ProductsWithAttributesDto(count, products, minPrice, maxPrice);

	}
}
