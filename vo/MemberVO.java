package kr.couple.web.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/*
 CREATE TABLE Member (
shared_id         varchar2(20) PRIMARY KEY,
shared_password   varchar2(20) NOT NULL,
member_name1         varchar2(20) NOT NULL,
email1             varchar2(50) NOT NULL,
birth1             DATE NOT NULL,
member_name2         varchar2(20) NOT NULL,
email2             varchar2(50) NOT NULL,
birth2             DATE NOT NULL
);
  */
@Data
public class MemberVO {
	private String	shared_id ;
	private String 	shared_password;
	private String 	member_name1;
	private String 	email1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	birth1;
	private String 	member_name2;
	private String 	email2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date 	birth2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date 	firstDay;
	// 테이블에는 없지만 로그인 화면에서 사용하기 위해서 등록 
	private boolean saveID;
}

