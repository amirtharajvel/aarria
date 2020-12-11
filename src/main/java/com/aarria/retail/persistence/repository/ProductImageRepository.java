package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

	@Query("select pc from ProductImage pc where pc.product = :product")
	public Set<ProductImage> findByProduct(@Param("product") Product product);
}
