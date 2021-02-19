package com.portfolio.cashbook.qna.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.portfolio.cashbook.qna.dao.QnaDAO;
import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.PagingCalculator;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="qnaDAO")
	private QnaDAO qnaDAO;


	@Override
	public List<Map<String, Object>> getQnaBoardList(PagingCriteriaVO criteriaVO, HttpSession session) throws Exception {
		
		// << 검색기능 >>
		String keyword; // 검색창에 입력한 키워드
		String select_type; // 검색 타입
		String sql_syntax; // 검색 타입에 따라 추가될 SQL 구문
		
		// 검색 타입별 SQL 구문 추가
		if(criteriaVO.getKeyword() != null) {
			
			keyword = criteriaVO.getKeyword();
			select_type = criteriaVO.getSelect_type();
			
			log.debug("검색 Keyword: "+keyword+", 검색 타입: "+select_type);
			
			switch(select_type) {
				case "sub+cont": // 검색 타입: 제목+내용
					sql_syntax = "AND T1.SUBJECT LIKE '%"+keyword+"%' OR T1.CONTENT LIKE '%"+keyword+"%'";
					break;
				case "sub": // 검색 타입: 제목
					sql_syntax = "AND T1.SUBJECT LIKE '%"+keyword+"%'";
					break;
				case "cont": // 검색 타입: 내용
					sql_syntax = "AND T1.CONTENT LIKE '%"+keyword+"%'";
					break;
				case "user": // 검색 타입: 작성자
					sql_syntax = "AND T2.USER_ID LIKE '%"+keyword+"%'";
					break;
				case "my_post": // 검색 타입: 내가 쓴 글
					UserVO userSession = (UserVO) session.getAttribute("userSession");
					sql_syntax = "AND T1.USER_IDX = "+userSession.getUser_idx();
					break;
				default:
					sql_syntax = null;
					break;
			}
			
			criteriaVO.setSql_syntax(sql_syntax);
			
		} // [끝] 검색 타입별 SQL 구문 추가 끝
		
		// 현재 페이지의 첫 번째 글 인덱스(SQL LIMIT절에 들어갈 숫자) 계산
		criteriaVO.calFirstContentIndex();
		
		log.debug("getCurrentPageNo: "+criteriaVO.getCurrentPageNo());
		log.debug("getContentPerPage: "+criteriaVO.getContentPerPage());
		log.debug("getFirstContentIndex: "+criteriaVO.getFirstContentIndex());
		log.debug("getKeyword: "+criteriaVO.getKeyword());
		log.debug("getSelect_type: "+criteriaVO.getSelect_type());
		log.debug("getSql_syntax: "+criteriaVO.getSql_syntax());
		log.debug("getStartPageNo: "+criteriaVO.getStartPageNo());
		log.debug("getUser_idx: "+criteriaVO.getUser_idx());
		log.debug("getFirstContentIndex: "+criteriaVO.getFirstContentIndex());
		
		return qnaDAO.getQnaList(criteriaVO);
	}
	
	public PagingCalculator getPagingData(PagingCriteriaVO criteriaVO) throws Exception {
		
		PagingCalculator cal = new PagingCalculator(criteriaVO);
		
		cal.setTotalContentCount(qnaDAO.getTotalContentCount());
		
		return cal;
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
