package com.portfolio.cashbook.qna.conroller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.cashbook.qna.service.QnaService;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class QnaBoardController {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="qnaService")
	private QnaService qnaService;
	
	// Q&A 게시판 리스트 페이지
	@RequestMapping(value="/qna/list.do")
	public String qna_list(Model model, @RequestParam(required=false) String keyword, @RequestParam(required=false) String select_type) throws Exception {
		
		String SQL_Syntax = null; // 검색 타입에 따라 추가될 SQL 구문
		List<Map<String, Object>> boardList; // 게시판 List
		
		log.debug("검색 Keyword: "+keyword+", 검색 타입: "+select_type);
		
		// 검색 타입별 SQL 구문 추가
		if(keyword != null) { 
			
			switch(select_type) {
				case "sub+cont": // 검색 타입: 제목+내용
					SQL_Syntax = "AND T1.SUBJECT LIKE '%"+keyword+"%' OR T1.CONTENT LIKE '%"+keyword+"%'";
					break;
				case "sub": // 검색 타입: 제목
					SQL_Syntax = "AND T1.SUBJECT LIKE '%"+keyword+"%'";
					break;
				case "cont": // 검색 타입: 내용
					SQL_Syntax = "AND T1.CONTENT LIKE '%"+keyword+"%'";
					break;
				case "user": // 검색 타입: 작성자
					SQL_Syntax = "AND T2.USER_ID LIKE '%"+keyword+"%'";
					break;
				default:
					break;
			}
			
		}

		// 게시판 리스트 불러오기
		boardList = qnaService.getQnaBoardList(SQL_Syntax);

		// Attribute 저장
		model.addAttribute("page","list");
		model.addAttribute("boardList", boardList);
		
		return "qnaboard/qna_board_main";
	}
	
	// 내가 쓴 글 페이지
	@RequestMapping(value="/qna/myPost.do")
	public String qna_my_post(Model model, HttpSession session) throws Exception{
		
		// userSession: User 정보 불러오기
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		
		log.debug("userSession.getUser_idx(): "+userSession.getUser_idx());
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList 
			= qnaService.getQnaBoardList("AND T1.USER_IDX = "+userSession.getUser_idx());
		
		// Attribute 저장
		model.addAttribute("page","my_post");
		model.addAttribute("boardList", boardList);
		
		return "qnaboard/qna_board_main";
	}
	
	// Q&A 게시판 글 읽기 페이지
	@RequestMapping(value="/qna/content.do")
	public String qna_content(Model model, @RequestParam String board_idx) throws Exception {
		
		log.debug("board_idx="+board_idx);
		
		// 게시판 내용 불러오기
		Map<String, Object> boardContent = qnaService.getQnaBoardContent(board_idx);
		
		// Attribute 저장
		model.addAttribute("page","content");
		model.addAttribute("boardContent", boardContent);
		
		return "qnaboard/qna_board_main";
	}
	
	// Q&A 게시판 글쓰기 페이지
	@RequestMapping(value="/qna/write.do")
	public String qna_write(Model model) throws Exception {
		
		// Attribute 저장
		model.addAttribute("page","write");
		
		return "qnaboard/qna_board_main";
	}
	
	// [INSERT] : Q&A 게시판 글쓰기 기능
	@RequestMapping(value="/qna/insertQnaContent.do")
	public String insert_qna_content(Model model, QnaBoardVO qnaVO, HttpSession session) throws Exception {
		
		// QnaVoardVO에 user_idx값 입력
		UserVO userSession = (UserVO) session.getAttribute("userSession");
		qnaVO.setUser_idx(userSession.getUser_idx());
		
		// Q&A 게시글 INSERT
		qnaService.insertQnaContent(qnaVO);
		log.debug("insert 완료!");
		
		// Attribute 저장
		model.addAttribute("page","content");
		//model.addAttribute("boardContent", boardContent);
		
		return "qnaboard/qna_board_main";
	}
	
}
