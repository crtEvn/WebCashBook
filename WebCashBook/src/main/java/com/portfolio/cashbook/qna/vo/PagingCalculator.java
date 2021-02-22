package com.portfolio.cashbook.qna.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PagingCalculator {
	
	private PagingCriteriaVO criteriaVO;
	private int totalContentCount; // 전체 게시글 개수
	private int totalPageCount; // 전체 페이지 개수(마지막 페이지 번호)
	private int pageSize; // 페이징 블록의 페이지 수
	private int firstPageNo; // 페이지 블록의 첫번 째 페이지 번호
	private int lastPageNo; // 페이지 블록의 마지막 페이지 번호
	private boolean hasPreviousBlock; // 이전 블록 존재 여부
	private boolean hasNextBlock; // 다음 블록 존재 여부
	private int nextBoard_idx; // 다음 게시글의 board_idx
	private int prevBoard_idx; // 이전 게시글의 board_idx
	 
	// 생성자
	public PagingCalculator(PagingCriteriaVO criteriaVO) {
		
		// 현재 페이지 번호
		if(criteriaVO.getCurrentPageNo() < 1) {
			criteriaVO.setCurrentPageNo(1);
		}
		
		// 페이지 당 출력할 개시글 수
		if(criteriaVO.getContentPerPage() < 1 || criteriaVO.getContentPerPage() > 100) {
			criteriaVO.setContentPerPage(15);
		}
		
		// 페이징 블록의 페이지 수
		pageSize = 10;
		
		this.criteriaVO = criteriaVO;
	}
	
	public void setTotalContentCount(int totalContentCount) {
		this.totalContentCount = totalContentCount;
		
		if(totalContentCount > 0) {
			calculation();
		}
	}
	
	private void calculation() {
		// 전체 페이지 수
		totalPageCount = ((totalContentCount-1) / criteriaVO.getContentPerPage()) + 1;
		if(criteriaVO.getCurrentPageNo() > totalPageCount) { 
			// 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 번호를 저장
			criteriaVO.setCurrentPageNo(totalPageCount);
		}
		
		// 페이지 블록의 첫번 째 페이지 번호
		firstPageNo = ((criteriaVO.getCurrentPageNo() - 1) / pageSize) * pageSize + 1;
		
		// 페이지 블록의 마지막 페이지 번호
		lastPageNo = firstPageNo + pageSize - 1;
		if(lastPageNo > totalPageCount) {
			lastPageNo = totalPageCount;
		}
		
		// 이전 블록 존재 여부
		hasPreviousBlock = criteriaVO.getCurrentPageNo() > pageSize;
		
		// 다음 블록 존재 여부
		hasNextBlock = criteriaVO.getCurrentPageNo() < totalPageCount - (totalPageCount%pageSize - 1);
		
	}
	
	public int getTotalContentCount() {
		return totalContentCount;
	}
	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public int getFirstPageNo() {
		return firstPageNo;
	}
	
	public int getLastPageNo() {
		return lastPageNo;
	}
	
	public boolean getHasPreviousBlock() {
		return hasPreviousBlock;
	}
	
	public boolean getHasNextBlock() {
		return hasNextBlock;
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

	public String toString() {
		
		return "\ntotalContentCount: "+totalContentCount
				+"\ntotalPageCount: "+totalPageCount
				+"\npageSize: "+pageSize
				+"\nfirstPageNo: "+firstPageNo
				+"\nlastPageNo: "+lastPageNo
				+"\nhasPreviousBlock: "+hasPreviousBlock
				+"\nhasNextBlock: "+hasNextBlock;
	}

}
