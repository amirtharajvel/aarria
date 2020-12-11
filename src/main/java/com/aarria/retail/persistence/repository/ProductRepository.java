package com.aarria.retail.persistence.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select distinct(p) from Product p where p.id in :ids order by p.addedDate desc")
	public Set<Product> findProductsByIds(@Param("ids") List<Long> ids);

	@Query("select p from Product p join p.productStock ps order by (ps.originalStock - ps.stock) ")
	public Set<Product> getPopularProducts();

	@Query("select p from Product p order by p.discount desc")
	public Set<Product> getBestOfferProducts();

	@Query("select distinct(p) from Product p order by p.addedDate desc")
	public Set<Product> findAllProductsOrderByAddedDate();

	@EntityGraph(value = "graph.product.stock", type = EntityGraphType.LOAD)
	public Optional<Product> findById(@Param("id") Long id);

	@EntityGraph(value = "graph.product.stock", type = EntityGraphType.LOAD)
	@Query("select distinct(p) from Product p join p.productStock ps where p.pid = :pid ")
	public Product findByPid(@Param("pid") String pid);

	@Query("select count(p.id), p.id, max(p.price), min(p.price) from Product p join p.productStock ps join p.categories cs where cs.id in :categoryIds AND p.price between :minPrice AND :maxPrice group by p.id")
	public List<Object[]> findCountByCategoryIds(@Param("categoryIds") Set<Long> categoryIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("select count(p.id), p.id, max(p.price), min(p.price) from Product p join p.productStock ps join p.categories cs where cs.id in :categoryIds AND p.price between :minPrice AND :maxPrice AND ps.stock > 0 group by p.id")
	public List<Object[]> findCountByCategoryIdsWithStock(@Param("categoryIds") Set<Long> categoryIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@EntityGraph(value = "graph.product.stock", type = EntityGraphType.LOAD)
	@Query("select distinct(p) from Product p join p.productStock ps join p.categories cs where cs.id in :categoryIds AND p.price between :minPrice AND :maxPrice")
	public List<Product> findByCategoryIds(@Param("categoryIds") Set<Long> categoryIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);

	@EntityGraph(value = "graph.product.stock", type = EntityGraphType.LOAD)
	@Query("select distinct(p) from Product p join p.productStock ps join p.categories cs where cs.id in :categoryIds AND p.price between :minPrice AND :maxPrice AND ps.stock > 0")
	public List<Product> findByCategoryIdsWithStock(@Param("categoryIds") Set<Long> categoryIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);

	@EntityGraph(value = "graph.product.attributes", type = EntityGraphType.LOAD)
	@Query("select new map(count(p.id), p.id) from Product p join p.productStock ps join p.attributes attr where attr.id in(:attributeIds) AND p.price between :minPrice AND :maxPrice group by p.id")
	public List<?> findCountByCategoryIdsAndAttributeIdsAndPrice(@Param("attributeIds") Set<Long> attributeIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("select p from Product p where p.id in :p")
	public List<?> findByAttributeIdsAndPrice(@Param("p") String p);

	@Query("select distinct(product) from Product product join product.productStock ps where product.id in(select distinct(p.id) from Product p join p.categories cs where cs.id in :categoryIds) ")
	public Set<Product> getAllRefinersByCategory(@Param("categoryIds") List<Long> categoryIds);

	@Query("select distinct(product) from Product product join product.productStock ps where product.id in(select distinct(p.id) from Product p join p.categories cs where cs.id in :categoryIds AND p.price between :minPrice AND :maxPrice)")
	public List<Product> getAllRefinersByCategoryAndAttributeIdAndPrice(@Param("categoryIds") List<Long> categoryIds,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);

	@Query("select distinct p.measureUnit from Product p join p.productStock ps")
	public List<String> getMeasurementUnits();

	@Query("select distinct(p) from Product p join p.productStock ps where ps.stock <= 0")
	public Set<Product> findSoldOutProducts();

	@Query(nativeQuery = true, value = "select * from product pp where pp.id in (select distinct(p.id) from product p join product_category pc join product_stock ps on p.id = pc.product_id and p.id = ps.product_id where pc.category_id = :categoryId)")
	public Collection<Product> findRandomProducts(@Param("categoryId") Long categoryId);
}
