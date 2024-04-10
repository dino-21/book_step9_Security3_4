package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total; // 전체 데이타 갯수(db저장된)
	private Criteria cri; // 몇페이지, 페이지당 갯수

	public PageDTO(Criteria cri, int total) {

		this.cri = cri;
		this.total = total; // 85개면 실제페이지는 9

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10; // 끝페이지 10

		this.startPage = this.endPage - 9; // 시작페이지

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount())); // 실제페이지 9

		if (realEnd <= this.endPage) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;

		this.next = this.endPage < realEnd;
	}
}
