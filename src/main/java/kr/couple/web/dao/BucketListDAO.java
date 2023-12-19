package kr.couple.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.couple.web.vo.BucketListVO;

@Mapper
public interface BucketListDAO {
	// <!-- 1. 전체 개수 얻기 : 페이징 -->
	int selectCount(Map<String, String> map);
	int selectCount2(Map<String, String> map);
	
	// <!-- 2. 1개 얻기 : 내용보기/수정/삭제 -->
	BucketListVO selectById(int id);
	
	//<!-- 3. 1페이지 얻기 : 목록보기 -->
	List<BucketListVO> selectList(Map<String, String> map);
	List<BucketListVO> selectList2(Map<String, String> map);
	
	// <!-- 4. 저장하기 -->
	void insert(BucketListVO bucketListVO);
	
	// <!-- 5. 수정하기 -->
	void update(BucketListVO bucketListVO);
	
	// <!-- 6. 삭제하기 -->
	void delete(int id);
	
	void updateComcheck(Map<String, String> map);
}
