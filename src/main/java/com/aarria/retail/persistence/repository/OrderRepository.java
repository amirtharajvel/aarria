package com.aarria.retail.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select distinct(o) from Order o join fetch o.orderedItems where o.id = :id")
	public Order findOneWithItems(@Param("id") Long id);

	@Query("select distinct(o) from Order o join fetch o.orderedItems where o.orderId = :orderId and o.user = :user")
	public Order findOneWithItems(@Param("orderId") String orderId, @Param("user") User user);

	public List<Order> findByUserAndStatus(User user, Integer status);

	@Query("select distinct(o) from Order o join fetch o.orderedItems where o.user = :user order by orderedDate desc")
	public List<Order> findByUser(@Param("user") User user);

	public Order findByOrderId(String orderId);

	public Integer countByStatus(@Param("status") Integer status);

	public List<Order> findByStatus(@Param("status") Integer status);

	@Query("select distinct(o) from Order o join fetch o.orderedItems where o.status in (:orderStatus)")
	public List<Order> getAllRefundsForOrder(@Param("orderStatus") List<Integer> orderStatus);

	@Query("select distinct(o) from Order o join fetch o.orderedItems where o.id in (select i.order.id from Item i where i.status in (:itemStatus))")
	public List<Order> getOrderByItemStatus(@Param("itemStatus") List<Integer> itemStatus);

	@Query("select distinct(o) from Order o join o.delivery d where o.status in (:status) and o.isDelivered = true and d.deliveredDate between :thirtyDaysBefore and :sevenDaysBefore")
	public List<Order> findExpiredOrders(@Param("status") List<Integer> status,
			@Param("thirtyDaysBefore") Date thirtyDaysBefore, @Param("sevenDaysBefore") Date sevenDaysBefore);
}
