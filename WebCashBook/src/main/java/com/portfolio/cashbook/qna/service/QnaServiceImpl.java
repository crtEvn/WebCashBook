package com.portfolio.cashbook.qna.service;

import java.util.HashMap;
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
import com.portfolio.cashbook.user.service.CheckUserService;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="qnaDAO")
	private QnaDAO qnaDAO;
	
	@Resource(name="checkUserService")
	private CheckUserService checkUserService;
	
	// 게시판 정보 불러오기
	@Override
	public QnaBoardVO getBoardData(QnaBoardVO qnaBoardVO, PagingCriteriaVO criteriaVO, HttpSession session) throws Exception {
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList = getQnaBoardList(criteriaVO, session);
		
		// 페이징 데이터 불러오기
		PagingCalculator pagingCal = getPagingData(criteriaVO, session);
		
		if(qnaBoardVO.getBoard_idx() != null) {
			// 이전글, 다음글의 board_idx 저장
			setNextPrevBoard_idx(qnaBoardVO.getBoard_idx(), criteriaVO);
			
			pagingCal.setNextBoard_idx(criteriaVO.getNextBoard_idx());
			pagingCal.setPrevBoard_idx(criteriaVO.getPrevBoard_idx());
			
			// 게시글 정보 불러오기
			Map<String, Object> boardContent = getQnaBoardContent(qnaBoardVO.getBoard_idx(), criteriaVO);
			
			qnaBoardVO.setBoardContent(boardContent);
		}
		
		qnaBoardVO.setBoardList(boardList);
		qnaBoardVO.setPagingCal(pagingCal);
		
		return qnaBoardVO;
	}

	@Override
	public String setSql_syntax(PagingCriteriaVO criteriaVO) throws Exception {
		
		String sql_syntax = "";
		
		if(criteriaVO.getKeyword() != null) {
		
			String keyword = criteriaVO.getKeyword();
			String select_type = criteriaVO.getSelect_type();
		
			switch(select_type) {
			case "sub_cont": // 검색 타입: 제목+내용
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
			default:
				sql_syntax = "";
				break;
			}
		
		}
		
		return sql_syntax;
	}

	// 게시판 리스트 불러오기
	@Override
	public List<Map<String, Object>> getQnaBoardList(PagingCriteriaVO criteriaVO, HttpSession session) throws Exception {
		
		// << 검색기능 >>
		String keyword; // 검색창에 입력한 키워드
		String select_type; // 검색 타입
		String sql_syntax = ""; // 검색 타입에 따라 추가될 SQL 구문
		
		// 검색 타입별 SQL 구문 추가
		sql_syntax = setSql_syntax(criteriaVO);
		
		// 내가 쓴 글을 조회한 경우
		if(criteriaVO.isMy_post()) {
			UserVO userSession = (UserVO) session.getAttribute("userSession");
			sql_syntax = sql_syntax + " AND T1.USER_IDX = "+userSession.getUser_idx();
		}
		
		// sql_syntax 저장
		criteriaVO.setSql_syntax(sql_syntax);
			
		// 현재 페이지의 첫 번째 글 인덱스(SQL LIMIT절에 들어갈 숫자) 계산
		criteriaVO.calFirstContentIndex();
		
		return qnaDAO.getQnaList(criteriaVO);
	}
	
	// 페이징 데이터 불러오기
	@Override
	public PagingCalculator getPagingData(PagingCriteriaVO criteriaVO, HttpSession session) throws Exception {
		
		String sql_syntax = "";
		PagingCalculator cal = new PagingCalculator(criteriaVO);
		
		// 검색 타입별 SQL 구문 추가
		if(criteriaVO.getKeyword() != null) {
			String keyword = criteriaVO.getKeyword();
			String select_type = criteriaVO.getSelect_type();
			
			sql_syntax = setSql_syntax(criteriaVO);
		}
		
		// 내가 쓴 글을 조회한 경우
		if(criteriaVO.isMy_post()) {
			UserVO userSession = (UserVO) session.getAttribute("userSession");
			sql_syntax = sql_syntax + " AND T1.USER_IDX = "+userSession.getUser_idx();
		}
		
		// sql_syntax 저장
		criteriaVO.setSql_syntax(sql_syntax);
		
		// 전체 게시글 수 저장
		cal.setTotalContentCount(qnaDAO.getTotalContentCount(criteriaVO));
		
		return cal;
	}
	
	// 삭제된 게시글인지 확인
	public String checkBoard_idx(String board_idx, PagingCriteriaVO criteriaVO) throws Exception {
		
		// 게시글 삭제 여부 확인
		Map<String, Object> checkDelete_yn = qnaDAO.checkDelete_yn(board_idx);
		
		if(checkDelete_yn.get("DELETE_YN").toString().equals("Y")) {
			// 삭제된 게시글일 경우
			board_idx = "0";
			return board_idx;
		}
				
		// 첫 번째, 마지막 째 게시글 번호 확인
		Map<String, Object> getMaxMinBoardIdx = qnaDAO.getMaxMinBoardIdx();
		
		int maxBoard_idx = Integer.parseInt(getMaxMinBoardIdx.get("MAX_BOARD_IDX").toString());
		int minBoard_idx = Integer.parseInt(getMaxMinBoardIdx.get("MIN_BOARD_IDX").toString());
		
		criteriaVO.setMaxBoard_idx(maxBoard_idx);
		criteriaVO.setMinBoard_idx(minBoard_idx);
		
		if(Integer.parseInt(board_idx) > maxBoard_idx || Integer.parseInt(board_idx) < minBoard_idx) {
			board_idx = "0";
		}
		
		return board_idx;
	}
	
	// 게시글 정보 불러오기
	@Override
	public Map<String, Object> getQnaBoardContent(String board_idx, PagingCriteriaVO criteriaVO) throws Exception {
		
		// board_idx 체크
		board_idx = checkBoard_idx(board_idx, criteriaVO);
		
		return qnaDAO.getQnaContent(board_idx);
	}
	
	// 다음글, 이전글의 board_idx 저장
	@Override
	public void setNextPrevBoard_idx(String board_idx, PagingCriteriaVO criteriaVO) throws Exception {
		
		Map<String, Object> nextPrevContentParam = new HashMap<String, Object>();
		Map<String, Object> getNextBoard_idx;
		Map<String, Object> getPrevBoard_idx;
		
		// 파라미터 Setting
		nextPrevContentParam.put("board_idx", board_idx);
		nextPrevContentParam.put("sql_syntax", setSql_syntax(criteriaVO));
		
		// 다음 게시글의 board_idx 저장
		getNextBoard_idx = qnaDAO.getNextQnaContent(nextPrevContentParam);
		
		if(getNextBoard_idx != null) {
			// 다음 게시글이 있으면 저장
			criteriaVO.setNextBoard_idx(Integer.parseInt(getNextBoard_idx.get("BOARD_IDX").toString()));
		} else {
			// 다음 게시글이 없으면 기존 board_idx 저장
			criteriaVO.setNextBoard_idx(Integer.parseInt(board_idx));
		}
		
		// 이전 게시글의 board_idx 저장
		getPrevBoard_idx = qnaDAO.getPrevQnaContent(nextPrevContentParam);
		
		if(getPrevBoard_idx != null) {
			// 이전 게시글이 있으면 저장
			criteriaVO.setPrevBoard_idx(Integer.parseInt(getPrevBoard_idx.get("BOARD_IDX").toString()));
		} else {
			// 이전 게시글이 없으면 기존 board_idx 저장
			criteriaVO.setPrevBoard_idx(Integer.parseInt(board_idx));
		}
		
	}
	
	// [INSERT]: 게시글 입력
	@Override
	public void insertQnaContent(QnaBoardVO vo) throws Exception {
		qnaDAO.insertQnaContent(vo);
	}
	
	// [UPDATE]: 게시글 삭제 처리
	public void deleteQnaContent(String board_idx) throws Exception {
		log.debug("게시글 삭제 처리, board_idx: "+board_idx);
		qnaDAO.deleteQnaContent(board_idx);
	}
	
	// 게시글 삭제 시 비밀번호 확인
	public boolean checkUser_pw(String user_pw, HttpSession session) throws Exception {
		
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		
		if(checkUserService.checkUser_PW(user_pw, userSession.getUser_pw())) {
			// 비밀번호 일치
			return true;
		}
		// 비밀번호 불일치
		return false;
	}

}
