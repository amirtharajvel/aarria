package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeRepository extends JpaRepository<Pincode, Long> {

	Pincode findByPincode(@Param("pincode") String pincode);

}
