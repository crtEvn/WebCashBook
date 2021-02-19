package com.portfolio.cashbook.qna.vo;

public class PagingCriteriaVO {
	
	private String keyword; // 검색 키워드
	private String select_type; // 검색 타입(제목+내용, 제목, 내용, 글쓴이)
	private String sql_syntax; // 검색 타입에 따라 추가될 SQL 구문
	private String user_idx; // 유저 index
	
	private int currentPageNo; // 현재 페이지 번호
	private int contentPerPage; // 페이지 당 출력할 개시글 수
	private int firstContentIndex; // 현재 페이지의 첫 번째 글 인덱스(SQL LIMIT절에 들어갈 숫자)
	
	// 생성자
	public PagingCriteriaVO() {
		this.select_type = "sub+cont";
		this.currentPageNo = 1;
		this.contentPerPage = 15;
		this.firstContentIndex = (currentPageNo - 1) * contentPerPage;
	}
	
	// 현재 블럭의 시작 페이지 번호
	public int getStartPageNo() {
		return (currentPageNo - 1) * contentPerPage;
	}
	
	// Getter, Setter Methods
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getSql_syntax() {
		return sql_syntax;
	}

	public void setSql_syntax(String sql_syntax) {
		this.sql_syntax = sql_syntax;
	}

	public String getSelect_type() {
		return select_type;
	}

	public void setSelect_type(String select_type) {
		this.select_type = select_type;
	}

	public String getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getContentPerPage() {
		return contentPerPage;
	}

	public void setContentPerPage(int contentPerPage) {
		this.contentPerPage = contentPerPage;
	}

	public int getFirstContentIndex() {
		return firstContentIndex;
	}

	public void setFirstContentIndex(int firstContentIndex) {
		this.firstContentIndex = firstContentIndex;
	}
	
	// firstContentIndex 계산 메서드
	public void calFirstContentIndex() {
		setFirstContentIndex((getCurrentPageNo() - 1) * getContentPerPage());
	}

}
