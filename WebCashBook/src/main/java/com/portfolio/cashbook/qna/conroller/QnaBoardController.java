package com.portfolio.cashbook.qna.conroller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.cashbook.qna.service.QnaService;
import com.portfolio.cashbook.qna.vo.PagingCalculator;
import com.portfolio.cashbook.qna.vo.PagingCriteriaVO;
import com.portfolio.cashbook.qna.vo.QnaBoardVO;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class QnaBoardController {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="qnaService")
	private QnaService qnaService;
	
	// Q&A 게시판 리스트 페이지
	@RequestMapping(value="/qna/list.do")
	public String qna_list(Model model, QnaBoardVO qnaBoardVO, PagingCriteriaVO criteriaVO) throws Exception {
		
		qnaBoardVO = (QnaBoardVO) qnaService.getBoardData(qnaBoardVO, criteriaVO, null);
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList = qnaBoardVO.getBoardList();
		
		// 페이징 데이터 불러오기
		PagingCalculator pagingCalc = qnaBoardVO.getPagingCal();
		
		// Attribute 저장
		model.addAttribute("page","list");
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingData", pagingCalc);
		
		return "qnaboard/qna_board_main";
	}
	
	// 내가 쓴 글 페이지
	@RequestMapping(value="/qna/myPost.do")
	public String qna_my_post(Model model, QnaBoardVO qnaBoardVO, PagingCriteriaVO criteriaVO, HttpSession session) throws Exception{
		
		// 내가 쓴 글 설정
		criteriaVO.setMy_post(true);
		
		qnaBoardVO = (QnaBoardVO) qnaService.getBoardData(qnaBoardVO, criteriaVO, session);
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList = qnaBoardVO.getBoardList();
		
		// 페이징 데이터 불러오기
		PagingCalculator pagingCalc = qnaBoardVO.getPagingCal();
		
		// Attribute 저장
		model.addAttribute("page","my_post");
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingData", pagingCalc);
		
		return "qnaboard/qna_board_main";
	}
	
	// Q&A 게시판 글 읽기 페이지
	@RequestMapping(value="/qna/content.do")
	public String qna_content(Model model, QnaBoardVO qnaBoardVO, PagingCriteriaVO criteriaVO) throws Exception {
		
		qnaBoardVO = (QnaBoardVO) qnaService.getBoardData(qnaBoardVO, criteriaVO, null);
		
		// 게시판 내용 불러오기
		Map<String, Object> boardContent = qnaBoardVO.getBoardContent();
		
		// 게시판 리스트 불러오기
		List<Map<String, Object>> boardList = qnaBoardVO.getBoardList();
		
		// 페이징 데이터 불러오기
		PagingCalculator pagingCalc = qnaBoardVO.getPagingCal();
		
		// Attribute 저장
		model.addAttribute("page","content");
		model.addAttribute("boardContent", boardContent);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagingData", pagingCalc);
		
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
