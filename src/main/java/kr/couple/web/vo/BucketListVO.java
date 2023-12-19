package kr.couple.web.vo;

import java.util.Date;

/*
CREATE TABLE bucketList (
   shared_id varchar2(20) PRIMARY KEY, -- 키필드
   list_no NUMBER NOT NULL, -- 원본글 번호
   member_name varchar2(20) NOT NULL, -- 작성자
   category varchar2(200) NOT NULL, -- 카테고리
   content varchar2(1000) NOT NULL, -- 내용   
   regdate timestamp DEFAULT sysdate, -- 작성일
   comcheck CHAR(1) CHECK(comcheck IN('1','0')), -- 완료
   comdate timestamp DEFAULT sysdate -- 완료일
);
*/

import lombok.Data;

@Data
public class BucketListVO {
	private int id;
	private String shared_id;
	private String member_name;
	private String category;
	private String content;
	private Date regdate;
	private boolean comcheck;
	private Date comdate;
}
