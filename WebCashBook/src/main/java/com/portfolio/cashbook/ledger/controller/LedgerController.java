package com.portfolio.cashbook.ledger.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.cashbook.ledger.service.LedgerService;
import com.portfolio.cashbook.sample.controller.SampleController;

@Controller
public class LedgerController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	@Resource(name="ledgerService")
	private LedgerService ledgerService;
	
	// Ledger Main Page : 가계부 내역을 보여줄 페이지
	@RequestMapping(value="/ledger/main.do")
	public String ledger_main(Model model) throws Exception {
		
		List<Map<String, Object>> ledgerList = ledgerService.getLedgerList();
		model.addAttribute("ledgerList",ledgerList);
		
		return "ledger/ledger_main";
	}
	
	

}
