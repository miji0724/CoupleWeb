package kr.couple.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.couple.web.dao.EventDAO;
import kr.couple.web.vo.EventVO;
import kr.couple.web.vo.EventsVO;
import lombok.extern.slf4j.Slf4j;

@Service("eventService")
@Slf4j
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDAO eventDAO;
	
	
	@Override
	public List<EventsVO> getList(String id) {
		log.info("getList() 호출");
		List<EventsVO> list = null;
		
		try {
			list=eventDAO.selectByList(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		log.info("getList() 리턴", list);
		return list;
	}

	@Override
	public EventVO getById(int id) {
		log.info("getById() 호출", id);
		EventVO eventVO = null;
		 try {
			 eventVO=eventDAO.selectById(id);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 log.info("getById() 리턴", id, eventVO);
		 return eventVO;
		
	}

	@Override
	public List<EventsVO> getByCategory(String category) {
		log.info("getByCategory() 호출", category);
		List<EventsVO> categoryList = null;
		 try {
			 categoryList=eventDAO.selectByCategory(category);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 log.info("getByCategory() 리턴", category, categoryList);
		 return categoryList;
	}

	@Override
	public boolean insert(EventVO vo) {
		log.info("insert({}) 호출", vo);
		boolean result = false;
		try {
			if(vo!=null) {
				eventDAO.insert(vo);
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("insert({}) 리턴", vo, result);
		return result;
	}

	@Override
	public boolean update(EventVO vo) {
		log.info("update({}) 호출", vo);
		boolean result = false;
		try {
			if(vo!=null) {
				eventDAO.update(vo);
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("update({}) 리턴", vo, result);
		return result;
	}

	@Override
	public boolean delete(int id) {
		log.info("delete({}) 호출", id);
		boolean result = false;
		try {
			eventDAO.delete(id);
			result=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		log.info("delete({}) 리턴", id, result);
		return result;
	}

}
