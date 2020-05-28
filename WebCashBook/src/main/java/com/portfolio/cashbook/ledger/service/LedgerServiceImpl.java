package com.portfolio.cashbook.ledger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portfolio.cashbook.ledger.dao.LedgerDAO;
import com.portfolio.cashbook.ledger.dto.LedgerDTO;
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
	
	@Override
	public List<Map<String, Object>> getDateGroup(LedgerDTO dto) throws Exception {
		return ledgerDAO.getDateGroup(dto);
	}

	@Override
	public List<Map<String, Object>> getLedgerByDate(LedgerDTO dto) throws Exception {
		return ledgerDAO.getLedgerByDate(dto);
	}
	
	@Override
	public Map<String, Object> getLedgerTotal(LedgerDTO dto) throws Exception {
		return ledgerDAO.getLedgerTotal(dto);
	}

	@Override
	public List<Map<String, Object>> getCalendarDateGroup(LedgerDTO dto) throws Exception {
		/* 필요한 값 : LedgerDTO.date(없으면 자동 설정됨) */
		
		Calendar cal = Calendar.getInstance();
		
		String strDate = null,daoDate = null;
		int year, month, day, intDate, endDay, startPosition;
		int preEndDay, preStartDay;
		int indexDateGroup = 0; // dateGroup List를 불러올때 사용할 인덱스
		
		// year, month 값 설정
		if(dto.getDate() == null) {
			// 날짜 값 없을 경우 오늘 년, 월 설정
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
		} else {
			try {
				year = Integer.parseInt(dto.getDate().substring(0, 4));
				month = Integer.parseInt(dto.getDate().substring(6, 7))-1;
				System.out.println("year,month: "+year+","+month);
			}catch(NumberFormatException e) {
				// dto.getDate()의 형식이 YYYY-MM가 아닌 경우
				return null;
			}
		}
		
		// endDay, startPosition 설정
		cal.set(year, month, 1);
		endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 월의 마지막 일
		startPosition = cal.get(Calendar.DAY_OF_WEEK); // 해당 월에서 1일의 위치(요일)
		
		// start_date(for DTO) 설정(달의 첫째 날, YYYY-MM-DD 형식)
		strDate = Integer.toString(year)+"-";
		strDate += Integer.toString(month+1).length()==1? "0"+Integer.toString(month+1)+"-01" : Integer.toString(month+1)+"-01";
		dto.setStart_date(strDate);
		
		// end_date(for DTO) 설정(달의 마지막 날, YYYY-MM-DD 형식)
		strDate = Integer.toString(year)+"-";
		strDate += Integer.toString(month+1).length()==1? "0"+Integer.toString(month+1)+"-" : Integer.toString(month+1)+"-";
		strDate += Integer.toString(endDay);
		dto.setEnd_date(strDate);
		
		// calDateGroup List에 데이터 입력
		List<Map<String,Object>> calDateGroup = new ArrayList();
		Map<String,Object> tempMap; // calDateGroup에 들어갈 임시 데이터
		List<Map<String,Object>> dateGroup = ledgerDAO.getDateGroup(dto);
		
		// 지난 달의 데이터 입력 - 1.변수 설정
		// preStartDay, preEndDay 설정
		cal.add(cal.MONTH, -1); // 한달 전
		preEndDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 지난달의 마지막 일
		preStartDay = preEndDay-startPosition+2; // 지난달의 마지막 주 시작일

		strDate = Integer.toString(cal.get(cal.YEAR))+"-";
		strDate += Integer.toString(cal.get(cal.MONTH)+1).length()==1? "0"+Integer.toString(cal.get(cal.MONTH)+1)+"-" : Integer.toString(cal.get(cal.MONTH)+1)+"-";
		
		// 지난 달의 데이터 입력 - 2.입력
		for(int index=1; index < startPosition; index++) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("DATE", strDate+preStartDay);
			tempMap.put("COLOR", "GRAY");
			
			calDateGroup.add(tempMap);
			
			preStartDay++;
		}
		
		// 이번달의 데이터 입력 - 1.변수 설정
		// int형 날짜 데이터 설정(달의 첫째 날, YYYYMMDD 형식)
		strDate = Integer.toString(year);
		strDate += Integer.toString(month+1).length()==1? "0"+Integer.toString(month+1)+"01" : Integer.toString(month+1)+"01";
		intDate = Integer.parseInt(strDate); // YYYYMMDD 형식의 날짜 데이터
				
		indexDateGroup = dateGroup.size()-1;
		
		// 이번달의 데이터 입력 - 2.입력
		for(int index=1; index <= endDay; index++) {
			
			if(indexDateGroup >= 0) {
				daoDate = String.valueOf(dateGroup.get(indexDateGroup).get("DATE")).replace("-","");
			}else {
				daoDate = "null";
			}
			
			// daoDate의 날짜(쿼리검색 결과)와 intDate의 날짜가 같을 경우
			// calDateGroup에 쿼리 데이터 추가
			if(daoDate.equals(Integer.toString(intDate))) {
				calDateGroup.add(dateGroup.get(indexDateGroup));
				indexDateGroup--;
			}else {
				StringBuilder sb = new StringBuilder(Integer.toString(intDate));
				sb.insert(4, "-");
				sb.insert(7, "-");
				
				tempMap = new HashMap<String, Object>();
				tempMap.put("DATE",sb);
				
				calDateGroup.add(tempMap);
			}
			
			intDate++;
		}
		
		// 다음달의 데이터 입력 - 1.변수 설정
		cal.add(cal.MONTH, +2);
		strDate = Integer.toString(cal.get(cal.YEAR))+"-";
		strDate += Integer.toString(cal.get(cal.MONTH)+1).length()==1? "0"+Integer.toString(cal.get(cal.MONTH)+1)+"-0" : Integer.toString(cal.get(cal.MONTH)+1)+"-0";
		day = 1;
		
		// 다음달의 데이터 입력 - 2. 입력
		for(int index=calDateGroup.size(); index < 42; index++) {
			tempMap = new HashMap<String, Object>();
			tempMap.put("DATE", strDate+day);
			tempMap.put("COLOR", "GRAY");
			
			calDateGroup.add(tempMap);
			day++;
		}
		
		return calDateGroup;
	}
	
}
