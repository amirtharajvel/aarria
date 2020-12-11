package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.BankAccount;
import com.aarria.retail.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	List<BankAccount> findByUser(@Param("user") User user);

	List<BankAccount> findByIfscCodeAndUser(@Param("ifscCode") String ifscCode, @Param("user") User user);
}
