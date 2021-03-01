package com.portfolio.cashbook.qna.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;
import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;

@Repository("qnaDAO")
public class QnaDAO extends AbstractDAO{
	
	public int getTotalContentCount(PagingCriteriaVO creteriaVO) {
		return (int)selectOne("qna.countQnaBoard", creteriaVO);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMaxMinBoardIdx() {
		return (Map<String, Object>)selectOne("qna.selectMaxMinBoardIdx");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getQnaList(PagingCriteriaVO creteriaVO) {
		return (List<Map<String, Object>>)selectList("qna.selectQnaBoardList", creteriaVO);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> checkDelete_yn(String board_idx) {
		return (Map<String, Object>)selectOne("qna.checkDelete_yn", board_idx);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getQnaContent(String board_idx) {
		return (Map<String, Object>)selectOne("qna.selectQnaBoardContent", board_idx);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getNextQnaContent(Map<String, Object> nextContentParam) {
		return (Map<String, Object>)selectOne("qna.selectNextQnaBoardContent", nextContentParam);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPrevQnaContent(Map<String, Object> prevContentParam) {
		return (Map<String, Object>)selectOne("qna.selectPrevQnaBoardContent", prevContentParam);
	}
	
	public void insertQnaContent(QnaBoardVO vo) {
		insert("qna.insertQnaBoardContent", vo);
	}
	
	public void deleteQnaContent(String board_idx) {
		update("qna.deleteQnaBoardContent", board_idx);
	}
}
