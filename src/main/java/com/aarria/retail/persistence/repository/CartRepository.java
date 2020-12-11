package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@EntityGraph(value = "graph.cart.product.stock", type = EntityGraphType.LOAD)
	@Query("select c from Cart c join fetch c.product where c.user = :user and c.isPaymentDone = false")
	public Set<Cart> findByUserWithStockAndProduct(@Param("user") User user);

	@Query("select c from Cart c where c.user = :user and c.checkoutType = :checkoutType")
	public List<Cart> findByUserCheckoutType(@Param("user") User user, @Param("checkoutType") String checkoutType);

	@EntityGraph(value = "graph.cart.product.stock", type = EntityGraphType.LOAD)
	@Query("select c from Cart c where c.user = :user and c.product.pid in :pids and c.size = :size and c.isPaymentDone = false")
	public Set<Cart> findByUserAndPidAndSize(@Param("user") User user, @Param("pids") List<String> pids,
			@Param("size") String size);

	// do not know why added distinct
	@EntityGraph(value = "graph.cart.product.stock", type = EntityGraphType.LOAD)
	@Query("select distinct(c) from Cart c where c.user = :user and c.product.pid in :pids and c.isPaymentDone = false")
	public List<Cart> findByUserAndPid(@Param("user") User user, @Param("pids") List<String> pids);
	
	@Query("select c from Cart c where c.user = :user")
	public Set<Cart> findByUser(@Param("user") User userId);

}
