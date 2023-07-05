package com.ezen.dto;

import lombok.Data;

@Data
public class Paging {

	private int page = 1;
	private int totalCount;
	private int beginPage;
	private int endPage;
	private int displayRow = 10;
	private int displayPage = 10;
	private boolean prev;
	private boolean next;
	private int startNum;
	private int endNum;

	// private void paging() { //기존에는 setTotal() 멤버메소드에서 호출해줬었다
	public void paging() { // 내가 수동으로 호출할수 있게 끔 public으로 바꾼다(@Data 때문에 getter/setter가 숨어있으니까 )
		endPage = ((int) Math.ceil(page / (double) displayPage)) * displayPage;
		beginPage = endPage - (displayPage - 1);
		int totalPage = (int) Math.ceil(totalCount / (double) displayRow);
		if (totalPage < endPage) {
			endPage = totalPage;
			next = false;
		} else {
			next = true;
		}
		prev = (beginPage == 1) ? false : true;
		startNum = (page - 1) * displayRow + 1;
		endNum = page * displayRow;
		System.out.println(beginPage + " " + endPage + " " + startNum + " " + endNum + " " + totalCount);

	}
}
