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
	public String qna_list(Model model) throws Exception {
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList = qnaService.getQnaBoardList();
		
		/*
		 * log.debug("boardList.size(): "+boardList.size());
		 * 
		 * for(int a=0; a<boardList.size(); a++) { log.debug(boardList.get(a)); }
		 */
		
		// Attribute 저장
		model.addAttribute("page","list");
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
