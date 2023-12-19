package kr.couple.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.couple.web.vo.EventVO;
import kr.couple.web.vo.EventsVO;

@Mapper
public interface EventDAO {
	//<!-- 1. 전체 개수 얻기 -->
	int selectCount();
	//<!-- 2. 전체가져오기 -->
	List<EventsVO> selectByList(String id);
	//<!-- 3. 1개 가져오기 : 이벤트수정, 삭제 등 -->
	EventVO selectById(int id);
	//<!-- 4. 카테고리별 이벤트 가져오기 -->
	List<EventsVO> selectByCategory(String category);
	//<!-- 5. 저장 -->
	void insert(EventVO eventVO);
	//<!-- 6. 수정 -->
	void update(EventVO eventVO);
	//<!-- 7. 삭제 -->
	void delete(int id);
}
