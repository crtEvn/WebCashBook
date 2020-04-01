package com.portfolio.cashbook.ledger.service;

import java.util.List;
import java.util.Map;

public interface LedgerService {
	
	List<Map<String, Object>> getLedgerList() throws Exception;
	
}
