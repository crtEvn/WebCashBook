package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.portfolio.cashbook.qna.dao.QnaDAO;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	
	@Resource(name="qnaDAO")
	private QnaDAO qnaDAO;

	@Override
	public List<Map<String, Object>> getQnaBoardList() throws Exception {
		return qnaDAO.getQnaList();
	}

	@Override
	public Map<String, Object> getQnaBoardContent(String board_idx) throws Exception {
		return qnaDAO.getQnaContent(board_idx);
	}

	@Override
	public void insertQnaContent(QnaBoardVO vo) throws Exception {
		qnaDAO.insertQnaContent(vo);
	}

}
