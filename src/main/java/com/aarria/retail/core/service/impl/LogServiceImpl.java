package com.aarria.retail.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Log;
import com.aarria.retail.core.service.LogService;
import com.aarria.retail.persistence.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;

	@Override
	public Log save(Log databaseLog) {
		return logRepository.save(databaseLog);
	}

}
