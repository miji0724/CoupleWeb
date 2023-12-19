package kr.couple.web.service;

import java.util.List;

import kr.couple.web.vo.MemberVO;

public interface MemberService {
	// 로그인
	MemberVO login(MemberVO vo);
	
	// 아이디 중복확인
	int getByMemberIdCount(String shared_id);

	// 이메일1 중복확인
	int getByEmail1Count(String email1);
	// 이메일2 중복확인
	int getByEmail2Count(String email2);
	
	// 1개 얻기
	MemberVO getByMemberId(String shared_id);
	
	// 모두 얻기
	List<MemberVO> getList();
	
	// 저장
	boolean insert(MemberVO VO);
	
	// 수정
	boolean update(MemberVO VO);
	
	// 삭제
	boolean deleteId(MemberVO VO);
	
	// 아이디 찾기
	MemberVO findMemberId(MemberVO VO);
	
	// 비밀번호 찾기
	MemberVO findPassword(MemberVO VO);
}
