package com.portfolio.cashbook.qna.vo;

public class PagingCriteriaVO {
	
	private String keyword; // 검색 키워드
	private String select_type; // 검색 타입(제목+내용, 제목, 내용, 글쓴이)
	private String sql_syntax; // 검색 타입에 따라 추가될 SQL 구문
	private String user_idx; // 유저 index
	private boolean isMy_post; // [내가 쓴 글]인 경우
	
	private int currentPageNo; // 현재 페이지 번호
	private int contentPerPage; // 페이지 당 출력할 개시글 수
	private int firstContentIndex; // 현재 페이지의 첫 번째 글 인덱스(SQL LIMIT절에 들어갈 숫자)
	private int maxBoard_idx; // 가장 최근의 게시글 인덱스
	private int minBoard_idx; // 가장 오래된 게시글 인덱스
	private int nextBoard_idx; // 다음 게시글의 board_idx
	private int prevBoard_idx; // 이전 게시글의 board_idx
	
	// 생성자
	public PagingCriteriaVO() {
		this.currentPageNo = 1;
		this.contentPerPage = 15;
		this.firstContentIndex = (currentPageNo - 1) * contentPerPage;
		this.isMy_post = false;
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
	
	public boolean isMy_post() {
		return isMy_post;
	}

	public void setMy_post(boolean isMy_post) {
		this.isMy_post = isMy_post;
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
	public int getMaxBoard_idx() {
		return maxBoard_idx;
	}

	public void setMaxBoard_idx(int maxBoard_idx) {
		this.maxBoard_idx = maxBoard_idx;
	}

	public int getMinBoard_idx() {
		return minBoard_idx;
	}

	public void setMinBoard_idx(int minBoard_idx) {
		this.minBoard_idx = minBoard_idx;
	}
	
	public int getNextBoard_idx() {
		return nextBoard_idx;
	}

	public void setNextBoard_idx(int nextBoard_idx) {
		this.nextBoard_idx = nextBoard_idx;
	}

	public int getPrevBoard_idx() {
		return prevBoard_idx;
	}

	public void setPrevBoard_idx(int prevBoard_idx) {
		this.prevBoard_idx = prevBoard_idx;
	}

	// firstContentIndex 계산 메서드
	public void calFirstContentIndex() {
		setFirstContentIndex((getCurrentPageNo() - 1) * getContentPerPage());
	}

}
