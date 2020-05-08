package com.portfolio.cashbook.ledger.service;

import java.util.List;
import java.util.Map;

import com.portfolio.cashbook.ledger.dto.LedgerDTO;
import com.portfolio.cashbook.ledger.vo.LedgerVO;

public interface LedgerService {
	
	List<Map<String, Object>> getLedgerList() throws Exception;
	
	void insertLedger(LedgerVO vo) throws Exception;
	
	void deleteLedger(String ledger_idx) throws Exception;
	
	void updateLedger(LedgerVO vo) throws Exception;
	
	List<Map<String, Object>> getDateGroup(LedgerDTO dto) throws Exception;
	
	List<Map<String, Object>> getLedgerByDate(LedgerDTO dto) throws Exception;
	
	Map<String, Object> getLedgerTotal(LedgerDTO dto) throws Exception;
}
