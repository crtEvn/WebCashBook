package com.portfolio.cashbook.ledger.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.cashbook.HomeController;
import com.portfolio.cashbook.ledger.service.LedgerService;
import com.portfolio.cashbook.ledger.vo.LedgerVO;
import com.portfolio.cashbook.sample.controller.SampleController;

@Controller
public class LedgerController {
	
	Log log = LogFactory.getLog(this.getClass());

	@Resource(name="ledgerService")
	private LedgerService ledgerService;
	
	// Ledger Main Page : 가계부 내역을 보여줄 페이지
	@RequestMapping(value="/ledger/main.do")
	public String ledger_main(Model model) throws Exception {
		
		List<Map<String, Object>> ledgerList = ledgerService.getLedgerList();
		model.addAttribute("ledgerList",ledgerList);
		
		return "ledger/ledger_main";
	}
	
	// [INSERT] : 가계부 내역 입력 기능
	@RequestMapping(value="/ledger/insertLedger.do")
	public String insert_ledger(Model model, LedgerVO vo) throws Exception {

		ledgerService.insertLedger(vo);
		
		return "redirect:/ledger/main.do";
	}
	
	// [DELETE] : 가계부 내역 삭제 기능
	@RequestMapping(value="/ledger/deleteLedger.do")
	public String delete_ledger(Model model, @RequestParam String ledger_idx) throws Exception {
		
		ledgerService.deleteLedger(ledger_idx);
		
		return "redirect:/ledger/main.do";
	}
	
	

}
