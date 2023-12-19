DROP SEQUENCE event_id_seq;
DROP TABLE events;

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

SELECT * FROM events;