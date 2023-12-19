package kr.couple.web.vo;

import lombok.Data;

@Data
public class EventsVO {
	private int id;
	private String title;
	private String username;
	private String start;
	private String end;
	private String category;
	private boolean allDay;
	private String color;
}
