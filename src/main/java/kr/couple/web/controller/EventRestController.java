package kr.couple.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.couple.web.service.EventService;
import kr.couple.web.vo.EventVO;
import kr.couple.web.vo.EventsVO;

@RestController
@RequestMapping(value="/api")
public class EventRestController {
	
	@Autowired
	private EventService eventService;
	
	// 목록보기
	@GetMapping("/list2")
	List<EventsVO> getList(@RequestParam(defaultValue = "") String id){		
		return eventService.getList(id);
	}
	
	
	// 카테고리별 일정보기
	@GetMapping("/{category}")
	List<EventsVO> getListByCategory(@PathVariable String category){
		return eventService.getByCategory(category);
	}
	
	// 새로운 이벤트 저장하기
	@PostMapping(value="/events")
	EventVO insert(@RequestBody EventVO vo) {
		eventService.insert(vo);
		return vo;
	}
	
	// 이벤트 수정하기
	@PutMapping(value="/events/{id}")
	EventVO update(@RequestBody EventVO vo, @PathVariable int id) {
		EventVO dbVO = eventService.getById(id);
		if(dbVO!=null) {
			eventService.update(vo);
		}
		return vo;
	}
	// 이벤트 삭제하기
	@DeleteMapping(value = "/events/{id}")
	EventVO delete(@PathVariable int id) {
		EventVO dbVO = eventService.getById(id);
		if(dbVO!=null) {
			eventService.delete(id);
		}
		return dbVO;
	}
}
