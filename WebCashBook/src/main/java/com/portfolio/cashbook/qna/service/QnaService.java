package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.PagingCalculator;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;

public interface QnaService {
	
	public QnaBoardVO getBoardData(QnaBoardVO qnaBoardVO, PagingCriteriaVO criteriaVO, HttpSession session) throws Exception;
	
	public PagingCalculator getPagingData(PagingCriteriaVO creteriaVO, HttpSession session) throws Exception;
	
	List<Map<String, Object>> getQnaBoardList(PagingCriteriaVO creteriaVO, HttpSession session) throws Exception;
	
	public String setSql_syntax(PagingCriteriaVO criteriaVO) throws Exception;
	
	Map<String, Object> getQnaBoardContent(String board_idx, PagingCriteriaVO criteriaVO) throws Exception;
	
	public void setNextPrevBoard_idx(String board_idx, PagingCriteriaVO criteriaVO) throws Exception;
	
	void insertQnaContent(QnaBoardVO vo) throws Exception;

}
