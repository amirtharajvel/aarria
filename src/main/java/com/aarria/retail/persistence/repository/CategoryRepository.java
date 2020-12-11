package com.aarria.retail.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("select c from Category c where c.id = :id AND c.active = :active order by c.parentCategory.id asc")
	public Category findByActive(@Param("active") Boolean active, @Param("id") Long id);

	@Query("select c from Category c join c.products p where p.id in(:productId) AND c.active = :active order by c.parentCategory.id asc")
	public List<Category> findByProductId(@Param("active") Boolean active, @Param("productId") Long productId);
}
