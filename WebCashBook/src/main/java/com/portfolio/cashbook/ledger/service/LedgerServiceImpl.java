package com.portfolio.cashbook.ledger.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portfolio.cashbook.ledger.dao.LedgerDAO;
import com.portfolio.cashbook.ledger.vo.LedgerVO;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService{
	
	@Resource(name="ledgerDAO")
	private LedgerDAO ledgerDAO;

	@Override
	public List<Map<String, Object>> getLedgerList() throws Exception {
		return ledgerDAO.getLedgerList();
	}

	@Override
	public void insertLedger(LedgerVO vo) throws Exception {
		ledgerDAO.insertLedger(vo);
	}

	@Override
	public void deleteLedger(String ledger_idx) throws Exception {
		ledgerDAO.deleteLedger(ledger_idx);
	}

	@Override
	public void updateLedger(LedgerVO vo) throws Exception {
		ledgerDAO.updateLedger(vo);
	}
	
	
	

}
