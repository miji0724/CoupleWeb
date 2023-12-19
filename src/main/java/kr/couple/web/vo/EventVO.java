package kr.couple.web.vo;

import lombok.Data;

@Data
public class EventVO {
	private int id;
	private String title;
	private String username;
	private String startDate;
	private String endDate;
	private String category;
	private boolean allDay;
	private String color;
}
