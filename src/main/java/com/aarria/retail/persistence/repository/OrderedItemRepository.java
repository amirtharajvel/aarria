package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<Item, Long> {

}