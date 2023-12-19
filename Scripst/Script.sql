CREATE SEQUENCE bucketList_seq;

-- 2. 버킷 리스트 테이블 생성
CREATE TABLE bucketList (
	id NUMBER PRIMARY KEY, -- 키필드
	shared_id varchar2(20) NOT NULL, -- 아이디
	member_name varchar2(20) NOT NULL, -- 작성자
	content varchar2(1000) NOT NULL, -- 내용   
	regdate timestamp DEFAULT sysdate, -- 작성일
	comcheck CHAR(1) DEFAULT '0' CHECK(comcheck IN('1','0')), -- 완료
	comdate timestamp DEFAULT sysdate -- 완료일
);

create sequence event_id_seq;
CREATE TABLE events (
	id number primary key,
	title varchar2(200) NOT NULL,
	username VARCHAR2 (200) NOT NULL,  
	startDate DATE NOT NULL,		
	endDate DATE NOT NULL,		
	category varchar2(200) NOT NULL,
	allDay CHAR(1) CHECK(allDay IN('1','0')),
	color varchar2(50) NOT null
);

CREATE TABLE member (
shared_id         varchar2(20) PRIMARY KEY,
shared_password   varchar2(20) NOT NULL,
member_name1         varchar2(20) NOT NULL,
email1             varchar2(50) NOT NULL,
birth1             DATE NOT NULL,
member_name2         varchar2(20) NOT NULL,
email2             varchar2(50) NOT NULL,
birth2             DATE NOT NULL,
firstDay		   DATE NOT NULL
);