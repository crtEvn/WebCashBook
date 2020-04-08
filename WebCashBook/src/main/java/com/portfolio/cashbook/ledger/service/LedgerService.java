package com.portfolio.cashbook.ledger.service;

import java.util.List;
import java.util.Map;

import com.portfolio.cashbook.ledger.vo.LedgerVO;

public interface LedgerService {
	
	List<Map<String, Object>> getLedgerList() throws Exception;
	
	void insertLedger(LedgerVO vo) throws Exception;
	
}
