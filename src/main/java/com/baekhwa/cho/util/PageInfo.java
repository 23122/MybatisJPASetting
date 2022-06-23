package com.baekhwa.cho.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PageInfo {
	private int start;
	private int end;
	private int total;

	private void calcPage(int pageNo) {
		// 보여줄 페이지 갯수
		int pageBlock = 10;
		// 페이지 갯수 묶음처리
		int blockNo = pageNo / pageBlock;
		if (pageNo % pageBlock != 0)
			blockNo++;
		// 1번페이지 {1,2,3...,10},2번페이지{11,12,13...,20}
		// 페이지 끝번호
		end = pageBlock * blockNo;// 출력페이지수*묶음번호
		start = end - pageBlock + 1;// 끝번호-출력페이지수+1
		// 마지막 페이지 번호
		if (end > total)
			end = total;
	}
	
	/**
	 * Mybatis인경우 사용하세요
	 * @param pageNo 현실페이지번호 입력
	 * @param rowTotal DB의 총 레코드 수를 넣어주세요. select count(*) from 테이블명
	 * @param limit 한 화면에 보여질 레코드 개수 지정하세요.
	 * @return PageInfo 객체생성됩니다.
	 */
	
	public static PageInfo getInstance(int pageNo, int rowTotal, int limit) {
		return new PageInfo(pageNo, rowTotal, limit);
	}

	private PageInfo(int pageNo, int rowTotal, int limit) {
		total = rowTotal / limit;// 페이지 갯수
		if (rowTotal % limit > 0)total++;// 패이지 갯수가 소수점일때 처리
		calcPage(pageNo);
	}
	
	/**
	 * JPA인경우 사용하세요
	 * @param pageNo 현실페이지번호 입력
	 * @param pageTotal Page객체에서 계산된 결과입력
	 * <br>
	 * ex)<br>
	 * Page<JpaBoardEntity> pageObj=repository.findAll(pageable);<br>
	 * int pageTotal=pageObj.getTotalPages();<br>
	 * <br>
	 * @return PageInfo 객체생성됩니다.
	 */
	
	public static PageInfo getInstance(int pageNo, int pageTotal) {
		return new PageInfo(pageNo, pageTotal);
	}

	private PageInfo(int pageNo, int pageTotal) {
		total = pageTotal;
		calcPage(pageNo);
	}
}
