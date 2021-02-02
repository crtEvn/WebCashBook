package com.portfolio.cashbook.ledger.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.cashbook.HomeController;
import com.portfolio.cashbook.ledger.dto.LedgerDTO;
import com.portfolio.cashbook.ledger.service.LedgerService;
import com.portfolio.cashbook.ledger.vo.LedgerVO;
import com.portfolio.cashbook.sample.controller.SampleController;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class LedgerController {
	
	Log log = LogFactory.getLog(this.getClass());

	@Resource(name="ledgerService")
	private LedgerService ledgerService;
	
	// Ledger Main Page : 가계부 내역을 보여줄 페이지
	@RequestMapping(value="/ledger/main.do")
	public String ledger_main(Model model, LedgerDTO dto, HttpSession session) throws Exception {
		
		// userSession: User 정보 불러오기
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		
		if(userSession == null) {
			return "redirect:/user/signin.do";
		}
		dto.setUser_idx(userSession.getUser_idx());
		
		// start_date, end_date값이 없을 경우 자동 설정
		if(dto.getStart_date() == null || dto.getEnd_date() == null) {
			LocalDate now = LocalDate.now();
			dto.setEnd_date(now.toString()); // 오늘 날짜
			dto.setStart_date(now.minusDays(30).toString()); // 30일 전 날짜
		}
		
		// 기간 내 가계부 총 수익, 지출 검색
		// (INCOME 총 수익, EXPENDITURE 총 지출, TOTAL 계:수입-지출)
		Map<String, Object> ledgerTotal = ledgerService.getLedgerTotal(dto);
		
		// 날짜별 가계부 그룹 검색
		// (DATE 날짜, CNT 날짜별 내역 개수, INCOME 수익, EXPENDITURE 지출)
		List<Map<String, Object>> dateGroup = ledgerService.getDateGroup(dto);
		
		// 날짜 그룹별 가계부 내역(ledgerByDate) 검색 후 ledgerList에 저장
		List<List<Map<String, Object>>> ledgerList = new ArrayList();
		List<Map<String, Object>> ledgerByDate = null;
		String date = null;
		
		for(int i=0; i < dateGroup.size(); i++) {
			date = dateGroup.get(i).get("DATE").toString();
			dto.setDate(date);
			ledgerByDate = ledgerService.getLedgerByDate(dto);
			ledgerList.add(ledgerByDate);
		}
		
		// Attribute 저장
		model.addAttribute("page","day");
		model.addAttribute("ledgerTotal", ledgerTotal);
		model.addAttribute("dateGroup", dateGroup);
		model.addAttribute("ledgerList", ledgerList);
		model.addAttribute("LedgerDTO", dto);
		
		return "ledger/ledger_main";
	}
	
	// 가계부 내역을 달력으로 보여주는 페이지
	@RequestMapping(value="/ledger/calendar.do")
	public String ledger_cal(Model model, LedgerDTO dto, HttpSession session) throws Exception {
		
		// userSession: User 정보 불러오기
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		
		// 로그인 정보 없을 경우 -> SignIn 페이지로 이동
		if(userSession == null) {
			return "redirect:/user/signin.do";
		}
		
		// dto.getDate() 값이 없을 경우 이번달로 설정
		if(dto.getDate() == null) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			dto.setDate(sdf.format(c.getTime()));
			log.debug("dto.getDate(): "+dto.getDate());
		}
		log.debug("dto.getDate(): "+dto.getDate());
		// Calendar List 불러오기
		dto.setUser_idx(userSession.getUser_idx());
		List<Map<String,Object>> calDateGroup = ledgerService.getCalendarDateGroup(dto);
		
		// Attribute 저장
		model.addAttribute("page","calendar");
		model.addAttribute("LedgerDTO", dto);
		model.addAttribute("calDateGroup", calDateGroup);
		
		return "ledger/ledger_main";
	}
	
	// [INSERT] : 가계부 내역 입력 기능
	@RequestMapping(value="/ledger/insertLedger.do")
	public String insert_ledger(Model model, LedgerVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		
		// LedgerVO에 user_id값 설정
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		vo.setUser_idx(userSession.getUser_idx());
		
		ledgerService.insertLedger(vo);
		
		// 이전 페이지 저장
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	// [DELETE] : 가계부 내역 삭제 기능
	@RequestMapping(value="/ledger/deleteLedger.do")
	public String delete_ledger(Model model, @RequestParam String ledger_idx) throws Exception {
		
		ledgerService.deleteLedger(ledger_idx);
		
		return "redirect:/ledger/main.do";
	}
	
	// [UPDATE] : 가계부 내역 수정 기능
	@RequestMapping(value="/ledger/updateLedger.do")
	public String update_ledger(Model model, LedgerVO vo) throws Exception {
		
		System.out.println(vo.getAmount()+","+vo.getLedger_idx());
		ledgerService.updateLedger(vo);
		
		return "redirect:/ledger/main.do";
	}
	
}
