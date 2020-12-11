package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Review;
import com.aarria.retail.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("select r from Review r where r.product.id = :productId")
	public List<Review> findByProduct(@Param("productId") Long productId);

	@Query("select r from Review r where r.product.pid = :productPid and r.user = :user")
	public List<Review> findByProductAndUser(@Param("productPid") String productPid, @Param("user") User user);
}
