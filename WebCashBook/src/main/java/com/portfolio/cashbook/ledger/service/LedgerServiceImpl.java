package com.portfolio.cashbook.ledger.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portfolio.cashbook.ledger.dao.LedgerDAO;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService{
	
	@Resource(name="ledgerDAO")
	private LedgerDAO ledgerDAO;

	@Override
	public List<Map<String, Object>> getLedgerList() throws Exception {
		return ledgerDAO.getLedgerList();
	}
	
	

}