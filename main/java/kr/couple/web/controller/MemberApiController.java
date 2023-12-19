package kr.couple.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.couple.web.service.MemberService;
import kr.couple.web.vo.MemberVO;

@RestController
@RequestMapping(value = "/api")
public class MemberApiController {

	@Autowired
	private MemberService memberService;
	// 목록
	@GetMapping(value = "/list")
	public List<MemberVO> getList(){
		return memberService.getList();
	}
	// 아이디로 찾기
	@GetMapping(value = "/list/{shared_id}")
	public MemberVO getShared_id(@PathVariable String shared_id){
		return memberService.getByMemberId(shared_id);
	}
	// 아이디 개수
	@GetMapping(value = "/count/{shared_id}")
	public int getMemberIdCount(@PathVariable String shared_id){
		return memberService.getByMemberIdCount(shared_id);
	}
	// 이메일1 개수
	@GetMapping(value = "/count/email1/{email1}")
	public int getEmail1Count(@PathVariable String email1){
		return memberService.getByEmail1Count(email1);
	}
	// 이메일2 개수
	@GetMapping(value = "/count/email2/{email2}")
	public int getEmail2Count(@PathVariable String email2){
		return memberService.getByEmail2Count(email2);
	}
	// 로그인
	@GetMapping("/login")
	public MemberVO login(@ModelAttribute MemberVO vo) {
		return memberService.login(vo);
	}
	
	
	
}
