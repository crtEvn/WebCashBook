package com.portfolio.cashbook.sample.dao;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {
	
	public String getTime() {
		return (String) selectOne("sample.selectNow");
	}
	
}
