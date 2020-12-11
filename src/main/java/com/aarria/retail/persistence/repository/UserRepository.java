package com.aarria.retail.persistence.repository;

import com.aarria.retail.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndEmailVerified(String email, Boolean emailVerified);

	public User findByMobileAndMobileVerified(String mobile, Boolean mobileVerified);

	public User findByEmailAndPassword(String email, String password);

	public User findByMobileAndPassword(String mobile, String password);
}
