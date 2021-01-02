package com.portfolio.cashbook.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;

@Repository("qnaDAO")
public class QnaDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getQnaList() {
		return (List<Map<String, Object>>)selectList("qna.selectQnaBoardList");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getQnaContent(String board_idx) {
		return (Map<String, Object>)selectOne("qna.selectQnaBoardContent", board_idx);
	}
}
