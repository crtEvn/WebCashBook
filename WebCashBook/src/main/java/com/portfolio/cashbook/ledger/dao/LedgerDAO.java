package com.portfolio.cashbook.ledger.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;
import com.portfolio.cashbook.ledger.vo.LedgerVO;

@Repository("ledgerDAO")
public class LedgerDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLedgerList() {
		return (List<Map<String, Object>>)selectList("ledger.selectLedgerList");
	}
	
	public void insertLedger(LedgerVO vo) {
		insert("ledger.insertLedger", vo);
	}

}
