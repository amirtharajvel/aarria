package com.aarria.retail.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aarria.retail.core.domain.Attribute;
import com.aarria.retail.core.domain.Category;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.web.dto.response.SearchDto;

@Repository
@Transactional
public class SearchRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AttributeRepository attributeRepository;

	private FullTextEntityManager em;

	public void startIndexing() {
		FullTextEntityManager em = Search.getFullTextEntityManager(entityManager);
		try {
			em.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			throw new RuntimeException("Error occurred during index rebuild - " + e);
		}
	}

	@Transactional
	public List<SearchDto> query(String string) {

		em = Search.getFullTextEntityManager(entityManager);

		List<SearchDto> dtos = new ArrayList<SearchDto>();

		dtos.addAll(getProducts(string));

		dtos.addAll(getCategories(string));

		// dtos.addAll(getAttributes(string)); //TODO - uncomment and search attribute
		// also

		System.out.println("All dtos " + dtos);

		return dtos;
	}

	@Transactional
	private List<SearchDto> getAttributes(String string) {

		QueryBuilder qb = em.getSearchFactory().buildQueryBuilder().forEntity(Attribute.class).get();

		org.apache.lucene.search.Query productQuery = qb.keyword().wildcard().onField("value").matching(string + "*")
				.createQuery();

		FullTextQuery query = em.createFullTextQuery(productQuery, Attribute.class);
		query.setProjection("id", "value", "product");
		query.setResultTransformer(Transformers.aliasToBean(Attribute.class));
		query.setMaxResults(5);

		// EntityGraph<Attribute> productGraph = em.createEntityGraph(Attribute.class);
		// productGraph.addSubgraph("product");
		//
		// query.setHint("javax.persistence.loadgraph", productGraph);

		@SuppressWarnings("unchecked")
		List<Attribute> attributes = query.getResultList();

		System.out.println("categories size=" + attributes.size() + " -> " + attributes);

		List<SearchDto> dtos = new ArrayList<SearchDto>();

		if (attributes != null && !attributes.isEmpty()) {

			for (Attribute p : attributes) {
				Attribute a = attributeRepository.findByIdWithProduct(p.getId());
				String pid = null;
				// if (a != null) {
				// if (a.getProduct() != null) {
				// pid = a.getProduct().getPid();
				// }
				// }

				SearchDto dto = new SearchDto(p.getId(), p.getValue(), "Attribute", pid);
				dtos.add(dto);
			}
		}

		return dtos;
	}

	@Transactional
	private List<SearchDto> getCategories(String string) {

		em = Search.getFullTextEntityManager(entityManager);

		QueryBuilder qb = em.getSearchFactory().buildQueryBuilder().forEntity(Category.class).get();

		org.apache.lucene.search.Query productQuery = qb.keyword().wildcard().onField("name").matching(string + "*")
				.createQuery();

		FullTextQuery query = em.createFullTextQuery(productQuery, Category.class);
		query.setProjection("id", "name");
		query.setResultTransformer(Transformers.aliasToBean(Category.class));
		query.setMaxResults(5);

		@SuppressWarnings("unchecked")
		List<Category> categories = query.getResultList();

		List<SearchDto> dtos = new ArrayList<SearchDto>();

		System.out.println("categories size=" + categories.size() + " -> " + categories);

		if (categories != null && !categories.isEmpty()) {

			for (Category p : categories) {
				SearchDto dto = new SearchDto(p.getId(), p.getName(), "Category", null);
				dtos.add(dto);
			}
		}

		return dtos;
	}

	@Transactional
	private List<SearchDto> getProducts(String string) {

		em = Search.getFullTextEntityManager(entityManager);

		QueryBuilder qb = em.getSearchFactory().buildQueryBuilder().forEntity(Product.class).get();

		org.apache.lucene.search.Query productQuery = qb.keyword().wildcard().onField("name").matching(string + "*")
				.createQuery();

		FullTextQuery query = em.createFullTextQuery(productQuery, Product.class);
		query.setProjection("id", "name", "pid");
		query.setResultTransformer(Transformers.aliasToBean(Product.class));
		query.setMaxResults(5);

		@SuppressWarnings("unchecked")
		List<Product> products = query.getResultList();

		System.out.println("categories size=" + products.size() + " -> " + products);

		List<SearchDto> dtos = new ArrayList<SearchDto>();

		if (products != null && !products.isEmpty()) {

			for (Product p : products) {
				SearchDto dto = new SearchDto(p.getId(), p.getName(), "Product", p.getPid());
				dtos.add(dto);
			}
		}

		return dtos;
	}
}
