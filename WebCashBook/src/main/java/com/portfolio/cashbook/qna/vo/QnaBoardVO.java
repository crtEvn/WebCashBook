package com.portfolio.cashbook.qna.vo;

import java.util.List;
import java.util.Map;

public class QnaBoardVO {
	
	private String board_idx;
	private String user_idx;
	private String subject;
	private String content;
	private String hit_cnt;
	private String reg_date;
	private String delete_yn;
	
	private Map<String, Object> boardContent;
	private List<Map<String, Object>> boardList;
	private PagingCalculator pagingCal;
	
	public String getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(String hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	
	public Map<String, Object> getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(Map<String, Object> boardContent) {
		this.boardContent = boardContent;
	}
	public List<Map<String, Object>> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<Map<String, Object>> boardList) {
		this.boardList = boardList;
	}
	public PagingCalculator getPagingCal() {
		return pagingCal;
	}
	public void setPagingCal(PagingCalculator pagingCal) {
		this.pagingCal = pagingCal;
	}
	
}
