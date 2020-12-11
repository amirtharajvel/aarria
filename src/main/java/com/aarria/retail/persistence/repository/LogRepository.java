package com.aarria.retail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aarria.retail.core.domain.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
