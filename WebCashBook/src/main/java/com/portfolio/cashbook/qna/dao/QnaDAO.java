package com.portfolio.cashbook.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;
import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;

@Repository("qnaDAO")
public class QnaDAO extends AbstractDAO{
	
	public int getTotalContentCount() {
		return (int)selectOne("qna.countQnaBoard");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getQnaList(PagingCriteriaVO creteriaVO) {
		return (List<Map<String, Object>>)selectList("qna.selectQnaBoardList", creteriaVO);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getQnaContent(String board_idx) {
		return (Map<String, Object>)selectOne("qna.selectQnaBoardContent", board_idx);
	}
	
	public void insertQnaContent(QnaBoardVO vo) {
		insert("qna.insertQnaBoardContent", vo);
	}
}
