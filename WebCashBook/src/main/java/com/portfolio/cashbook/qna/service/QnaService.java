package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

import com.portfolio.cashbook.qna.vo.QnaBoardVO;

public interface QnaService {
	
	List<Map<String, Object>> getQnaBoardList(String SQL_Syntax) throws Exception;
	
	Map<String, Object> getQnaBoardContent(String board_idx) throws Exception;
	
	void insertQnaContent(QnaBoardVO vo) throws Exception;

}
