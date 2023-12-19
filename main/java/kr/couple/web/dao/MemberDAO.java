package kr.couple.web.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.couple.web.vo.MemberVO;

@Mapper
public interface MemberDAO {
	// <!-- 1. 전체 개수 얻기 -->
	int selectCount();
	// <!-- 2. 지정 아이디의 개수 얻기 : 아이디 중복 확인 처리 -->
	int selectByMemberIdCount(String shared_id);
	
	int selectByEmail1Count(String email1);
	int selectByEmail2Count(String email2);
	
	// <!-- 3. 1개 가져오기 :로그인/정보수정/회원탈퇴 ... -->
	MemberVO selectByMemberId(String shared_id);
	MemberVO selectByMember_name(MemberVO memberVO);
	
	// <!-- 4. 전체가져오기 : 관리자가 회원 목록보기(나중에 조건별, 페이징 처리) -->
	List<MemberVO> selectByList();
	// <!-- 5. 저장 -->
	void insert(MemberVO memberVO);
	// <!-- 6. 수정 -->
	void update(MemberVO memberVO);
	// <!-- 7. 삭제 -->
	void deleteId(MemberVO memberVO);
	// <!-- 8. 임시 비밀번호로 바꾸기 -->
	void updatePassword(HashMap<String, String> map) ;
}
