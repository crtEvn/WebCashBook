package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

public interface QnaService {
	
	List<Map<String, Object>> getQnaBoardList() throws Exception;
	
	Map<String, Object> getQnaBoardContent(String board_idx) throws Exception;

}
