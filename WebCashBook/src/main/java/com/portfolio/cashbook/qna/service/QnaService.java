package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.PagingCalculator;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;

public interface QnaService {
	
	public PagingCalculator getPagingData(PagingCriteriaVO creteriaVO) throws Exception;
	
	List<Map<String, Object>> getQnaBoardList(PagingCriteriaVO creteriaVO, HttpSession session) throws Exception;
	
	Map<String, Object> getQnaBoardContent(String board_idx) throws Exception;
	
	void insertQnaContent(QnaBoardVO vo) throws Exception;

}
