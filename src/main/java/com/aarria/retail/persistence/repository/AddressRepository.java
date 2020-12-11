package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("select a from Address a where a.user = :user order by a.updatedDate desc")
	public List<Address> findAddresses(@Param("user") User user);

	public Address findByIdAndUser(@Param("id") Long id, @Param("user") User user);

	@Query("select a from Address a where a.user = :user order by a.updatedDate desc")
	public List<Address> findTop1ByUserOrderByUpdatedDateDesc(@Param("user") User user);
}
