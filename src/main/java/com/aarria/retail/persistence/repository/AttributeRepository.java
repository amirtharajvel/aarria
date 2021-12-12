package com.aarria.retail.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	@Query("select distinct a.value from Attribute a where a.refiner = :refiner")
	public List<String> getAllUniqueRefinersByName(@Param("refiner") String refiner);

	@Query("select a from Attribute a")
	public Attribute findByIdWithProduct(@Param("id") Long id);

	@Query("select a from Attribute a where a.refiner = :refiner and a.value = :value and a.category.id in :categoryIds")
	public List<Attribute> findByRefinerAndValueAndCategory(@Param("refiner") String refiner, @Param("value") String value,
			@Param("categoryIds") Set<Long> categoryIds);

	@Query("select distinct a from Attribute a where a.refiner = :refiner and a.value = :value")
	public Set<Attribute> findByRefinerAndValue(@Param("refiner") String refiner, @Param("value") String value);

	@Query("select distinct a from Attribute a inner join a.category c where c.id in (:categoryIds)")
	public Set<Attribute> findByCategoryIds(@Param("categoryIds") Set<Long> categoryIds);

	@Query("select a from Attribute a where a.id in (:ids)")
	public Set<Attribute> findByIds(@Param("ids") List<Long> ids);

}
