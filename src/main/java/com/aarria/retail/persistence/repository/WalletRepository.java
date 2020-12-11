package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	public Wallet findByUser(User user);

}
