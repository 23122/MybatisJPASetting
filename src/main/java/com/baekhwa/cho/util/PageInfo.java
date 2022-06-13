package com.baekhwa.cho.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PageInfo {
	private int start;
	private int end;
	private int total;
	
	public static PageInfo getInstance(int pageNo, int rowTotal, int limit) {
		return new PageInfo(pageNo, rowTotal, limit);
	}
	
	public PageInfo(int pageNo, int rowTotal, int limit) {
		total = rowTotal / limit;// 페이지 갯수
		if (rowTotal % limit > 0)total++;// 패이지 갯수가 소수점일때 처리
		// 보여줄 페이지 갯수
		int pageBlock = 10;
		// 페이지 갯수 묶음처리
		int blockNo = pageNo / pageBlock;
		if (pageNo % pageBlock != 0)blockNo++;
		// 1번페이지 {1,2,3...,10},2번페이지{11,12,13...,20}
		// 페이지 끝번호
		end = pageBlock * blockNo;// 출력페이지수*묶음번호
		start = end - pageBlock + 1;// 끝번호-출력페이지수+1
		//마지막 페이지 번호
		if(end>total) end=total;
	}
}
