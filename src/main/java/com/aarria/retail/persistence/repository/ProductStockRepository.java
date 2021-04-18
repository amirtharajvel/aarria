package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

	@Query("select ps from ProductStock ps where ps.product = :product and ps.size = :size and ps.stock > 0")
	ProductStock findByProductAndSize(@Param("product") Product product, @Param("size") String size);

	@Query("select ps from ProductStock ps where ps.product = :product and ps.size = :size")
	ProductStock findByProductAndSizeWithZeroStock(@Param("product") Product product, @Param("size") String size);
}
