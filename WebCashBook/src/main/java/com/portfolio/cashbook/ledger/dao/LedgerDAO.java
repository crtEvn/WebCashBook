package com.portfolio.cashbook.ledger.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;

@Repository("ledgerDAO")
public class LedgerDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLedgerList() {
		return (List<Map<String, Object>>)selectList("ledger.selectLedgerList");
	}

}
