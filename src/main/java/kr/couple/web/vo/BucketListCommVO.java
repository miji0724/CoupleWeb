package kr.couple.web.vo;

import lombok.Data;
// 컨트롤러에서 변수값을 받을때 사용

@Data
public class BucketListCommVO {
	private int p;  // 현재페이지
	private int s;  // 페이지당 글수
	private int b;  // 하단 페이지 개수
	private int id; // 글번호
	
	private int pb;  // 현재페이지
	private int sb;  // 페이지당 글수
	private int bb;  // 하단 페이지 개수
}
