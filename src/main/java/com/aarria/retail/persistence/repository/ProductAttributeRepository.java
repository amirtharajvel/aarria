package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

	@Query("select distinct a.value from Attribute a where a.refiner = :refiner")
	public List<String> getAllUniqueRefinersByName(@Param("refiner") String refiner);
	
}
