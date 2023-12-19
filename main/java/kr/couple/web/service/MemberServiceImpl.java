package kr.couple.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.couple.web.dao.MemberDAO;
import kr.couple.web.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Service("memberService")
@Slf4j
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberVO login(MemberVO vo) {
		log.info("login({}) 호출", vo);
		MemberVO memberVO = null;
		try {
			// 1. 넘어온 아니디가 존재하는지 판단
			MemberVO mvo = memberDAO.selectByMemberId(vo.getShared_id());
			if(mvo!=null) { // 지정 아이디의 회원이 있다면
				if(mvo.getShared_password().equals(vo.getShared_password())) {
					memberVO = mvo;
				}else {
					// 아이디는 있는데 비번이 틀리다.
				}
			}else {
				// 아이디가 없다
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("login({}) 리턴", vo, memberVO);
		return memberVO;
	}


	@Override
	public MemberVO getByMemberId(String shared_id) {
		log.info("getByMemberId({}) 호출", shared_id);
		MemberVO memberVO = null;
		
		try {
			memberVO = memberDAO.selectByMemberId(shared_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("getByMemberId({}) 리턴 : {}", shared_id, memberVO);
		return memberVO;
	}

	@Override
	public List<MemberVO> getList() {
		log.info("getList() 호출");
		List<MemberVO> list = null;
		
		try {
			list = memberDAO.selectByList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("getList() 리턴 : {} " + list);
		return list;
	}

	@Override
	public boolean insert(MemberVO VO) {
		log.info("insert({}) 호출", VO);
		boolean result = false;
		
		try {
			if(VO!=null) {
				memberDAO.insert(VO);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("insert({}) 리턴 : {}", VO, result);
		return result;
	}

	@Override
	public boolean deleteId(MemberVO VO) {
		log.info("deleteId({}) 호출", VO);
		boolean result = false;
		try {
			// 1. 넘어온 아니디가 존재하는지 판단
			MemberVO mvo = memberDAO.selectByMemberId(VO.getShared_id());
			if(mvo!=null) { // 지정 아이디의 회원이 있다면
				if(mvo.getShared_password().equals(VO.getShared_password())) {
					memberDAO.deleteId(VO);
					result = true ;
				}else {
					// 아이디는 있는데 비번이 틀리다.
					log.info("비밀번호가 틀렸습니다");
					
				}
			}else {
				// 아이디가 없다
				log.info("아이디가 없습니다");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("deleteId({}) 리턴 : {}", VO, result);
		return result;
	}
	
	@Override
	public boolean update(MemberVO VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberVO findMemberId(MemberVO VO) {
		log.info("findMemberId({}) 호출", VO);
		MemberVO memberVO = null;
		
		try {
			// 아이디로 찾기
			MemberVO dbVO = memberDAO.selectByMember_name(VO);
			if(dbVO!=null) { // 아이디가 있고 이메일도 같으면
					memberVO = dbVO; 
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("findMemberId({}) 리턴 : {}", VO, memberVO);
		return memberVO;
	}
	
	@Override
	public MemberVO findPassword(MemberVO VO) {
		log.info("findPassword({}) 호출", VO);
		MemberVO memberVO = null;
		
		try {
			// 아이디로 찾기
			MemberVO dbVO = memberDAO.selectByMemberId(VO.getShared_id());
			if(dbVO!=null) { // 아이디가 있고
				// 이메일도 같으면
				if(dbVO.getEmail1().equals(VO.getEmail1())) {
					
					// 임시 비밀번호를 만들고
					String newPassword = MakePassword.makePassword(10) ;
					HashMap<String, String> map = new HashMap<>() ;
					map.put("shared_id", dbVO.getShared_id()) ;
					map.put("shared_password", newPassword) ;
					memberDAO.updatePassword(map);
					
					dbVO.setShared_password(newPassword);
					
					memberVO = dbVO; 
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("findPassword({}) 리턴 : {}", VO, memberVO);
		return memberVO;
	}


	@Override
	public int getByMemberIdCount(String shared_id) {
		log.info("getByMemberIdCount({}) 호출", shared_id);
		int count = 0;
		try {
			count = memberDAO.selectByMemberIdCount(shared_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("getByMemberIdCount({}) 리턴 : {}", shared_id, count);
		return count;
	}




	@Override
	public int getByEmail1Count(String email1) {
		log.info("getByEmail1Count({}) 호출", email1);
		int count = 0;
		try {
			count = memberDAO.selectByEmail1Count(email1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("getByEmail1Count({}) 리턴 : {}", email1, count);
		return count;
	}
	
	@Override
	public int getByEmail2Count(String email2) {
		log.info("getByEmail2Count({}) 호출", email2);
		int count = 0;
		try {
			count = memberDAO.selectByEmail2Count(email2);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("getByEmail2Count({}) 리턴 : {}", email2, count);
		return count;
	}




}










	
	

