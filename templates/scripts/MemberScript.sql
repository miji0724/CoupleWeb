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

DROP TABLE MEMBER ;
INSERT INTO MEMBER 
VALUES (
	'id1', '123', '이름1', '1@naver.com', '1990-01-15', '이름2', '2@naver.com',  '1990-01-15', '2023-09-20' 
)

SELECT * FROM MEMBER ;

TO_DATE('1990-01-15', 'YYYY-MM-DD')