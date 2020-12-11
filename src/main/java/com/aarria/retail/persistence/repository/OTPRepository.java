package com.aarria.retail.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {

	public List<OTP> findOneByMobile(@Param("mobile") String mobile);
}
