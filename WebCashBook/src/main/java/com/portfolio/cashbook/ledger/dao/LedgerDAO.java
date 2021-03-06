package com.portfolio.cashbook.ledger.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;
import com.portfolio.cashbook.ledger.dto.LedgerDTO;
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
	
	public void deleteLedger(String ledger_idx) {
		delete("ledger.deleteLedger", ledger_idx);
	}
	
	public void updateLedger(LedgerVO vo) {
		update("ledger.updateLedger", vo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDateGroup(LedgerDTO dto) {
		return (List<Map<String, Object>>)selectList("ledger.selectDateGroup", dto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLedgerByDate(LedgerDTO dto) {
		return (List<Map<String, Object>>)selectList("ledger.selectLedgerByDate", dto);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getLedgerTotal(LedgerDTO dto) {
		return (Map<String, Object>)selectOne("ledger.selectLedgerTotal", dto);
	}

}
