package kr.couple.web.service;

import java.util.List;

import kr.couple.web.vo.EventVO;
import kr.couple.web.vo.EventsVO;

public interface EventService {
	
	// 이벤트 모두가져오기
	List<EventsVO> getList(String id);
	// 이벤트 1개 가져오기
	EventVO getById(int id);
	// 카테고리별 이벤트 가져오기
	List<EventsVO> getByCategory(String category);
	// 저장하기
	boolean insert(EventVO vo);
	// 수정하기
	boolean update(EventVO vo);
	// 삭제하기
	boolean delete(int id);
	
	
}
