package com.portfolio.cashbook.sample.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portfolio.cashbook.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	@Override
	public String getTime() throws Exception {
		return sampleDAO.getTime();
	}
	
	
	
}
