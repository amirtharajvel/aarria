package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Integer countByStatus(@Param("status") Integer status);
	
}
