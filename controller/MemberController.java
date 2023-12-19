package kr.couple.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.couple.web.service.MemberService;
import kr.couple.web.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	// 로그인 폼 처리하기
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		// 쿠키에 저장된 사용자아이디가 있으면 읽어서 간다.
		Cookie[] cookies = request.getCookies();
		String shared_id = null;
		// shared_id변수에 쿠키에 shared_id가 있다면 읽어서 대입하자
		if(cookies!=null && cookies.length>0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("shared_id")) {
					shared_id = cookie.getValue();
					break;
				}
			}
		}
		// 모델에 쿠키값을 저장한다.
		model.addAttribute("shared_id", shared_id);
		// 로그인 폼으로 포워딩 한다.
		return "/member/login";
	}
	
	// 로그인 처리하기
	@GetMapping(value = "/loginOk")
	public String loginOk(Model model) {
		return "redirect:/";
	}
	
	@PostMapping(value = "/loginOk")
	public String loginOkPost(@ModelAttribute MemberVO memberVO, Model model
			, HttpServletRequest request
			, HttpServletResponse response
			, HttpSession session) {
		if(memberVO!=null) {
			// 서비스를 호출하여 로그인을 수행한다.
			MemberVO dbVO = memberService.login(memberVO);
			if(dbVO!=null) { // 로그인에 성공했다면
				// 세션에 회원정보를 저장을 한다.
				session.setAttribute("mvo", dbVO);
				// 아이디 자동저장 처리
				Cookie cookie = null;
				if(memberVO.isSaveID()) { // 자동저장이라면
					cookie = new Cookie("shared_id", dbVO.getShared_id());
					cookie.setMaxAge(60*60*24*7); // 초단위로 휴효기간 지정
				}else { // 자동저장이 아니라면
					cookie = new Cookie("shared_id","");
					cookie.setMaxAge(0);
				}
				// 쿠키를 저장
				response.addCookie(cookie);
				
			}else {// 로그인에 실패했다면 로그인 폼으로 다시 보낸다.
				return "redirect:/member/login";
			}
		}
		return "redirect:/";
	}
	
	// 로그 아웃 처리
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		// 세션에 저장된 회원 정보를 지워버린다.
		session.removeAttribute("mvo");
		return "redirect:/";
	}
	
	// 회원 가입 폼 처리
	@GetMapping(value = "/join")
	public String join() {
		return "/member/join";
	}
	// 회원 가입 처리
	@GetMapping("/joinOk")
	public String joinOkGet() {
		return "redirect:/";
	}
	
	@PostMapping("/joinOk")
	public String joinOkPost(@ModelAttribute MemberVO memberVO) {
		if(memberVO!=null) {
			// 서비스를 호출하여 저장을 수행한다.
			memberService.insert(memberVO);
		}
		return "redirect:/member/login";
	}
	
	// 아이디 찾기 폼 
	@GetMapping(value = "/findMemberId")
	public String findMemberId() {
		return "/member/findMemberId";
	}

	// 아이디 찾기 실행
	@GetMapping(value = "/findMemberIdOk")
	public String findMemberIdOkGet() {
		return "redirect:/";
	}

	@PostMapping(value = "/findMemberIdOk")
	public String findMemberIdOkPost(@ModelAttribute MemberVO vo, Model model) {
		// 사용자 이름과 이메일 받음
		MemberVO dbVO = memberService.findMemberId(vo);
		if(dbVO==null) {
			return "redirect:/member/findMemberId";
		}
		model.addAttribute("vo", dbVO);
		return "/member/viewMemberId";
	}
	
	// 비밀번호 찾기 폼
	@GetMapping(value = "/findPassword")
	public String findPassword() {
		return "/member/findPassword";
	}
	
	// 비밀번호 찾기 실행
	@GetMapping(value = "/findPasswordOk")
	public String findPasswordGet() {
		return "redirect:/";
	}
	
	// 디데이 띄우기
	
	@GetMapping(value = "/dday")
	public String dday(@RequestParam String shared_id, Model model) {
		MemberVO memberVO = memberService.getByMemberId(shared_id);
		model.addAttribute("vo", memberVO);
		return "/member/dday";
	}
	
	// 캘린더 띄우기
	@GetMapping(value = "/calendar")
	public String calendar(@RequestParam String shared_id, Model model) {
		MemberVO memberVO = memberService.getByMemberId(shared_id);
		model.addAttribute("vo", memberVO);
		return "/member/calendar";
	}
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostMapping(value = "/findPasswordOk")
	public String findPasswordPost(@ModelAttribute MemberVO vo, Model model) throws MessagingException {
		MemberVO dbVO = memberService.findPassword(vo);
		if(dbVO == null) {
			// 일치하지 않는다면
			return "redirect:/member/findPassword";
		}
		// 일치하면
		// 메일을 발송하고
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		helper.setFrom("miji8450@naver.com");
		helper.setTo(dbVO.getEmail1());
//		helper.setTo(dbVO.getEmail2());
		helper.setSubject(dbVO.getShared_id() + "님 비밀번호 안내입니다.");
		// 메일 내용 만들기
		StringBuffer sb = new StringBuffer();
		sb.append(dbVO.getShared_id() + "님 비밀번호 안내입니다.<br>");
		sb.append(dbVO.getShared_id() + "님 비밀번호는 " + dbVO.getShared_password() + "입니다.<br>");
		helper.setText(sb.toString(), true);
		
		// 메일 발송
		javaMailSender.send(message);
		
		model.addAttribute("vo", dbVO);
		return "/member/viewPassword";
	}
	
	// 회원 탈퇴 폼 처리
	@GetMapping(value = "/deleteId")
	public String delete() {
		return "/member/deleteId";
	}
	// 회원 탈퇴 처리
	@GetMapping("/deleteIdOk")
	public String deleteOkGet() {
		return "redirect:/";
	}
	
	@PostMapping("/deleteIdOk")
	public String deleteOkPost(@ModelAttribute MemberVO memberVO) {
		if(memberVO!=null) {
			// 서비스를 호출하여 탈퇴를 수행한다.
			if(memberService.deleteId(memberVO)) {
				return "redirect:/member/logout" ;
			}else {
				return "redirect:/member/deleteId" ;
			}
		}
		return "/member/deleteId";
	}
	
	
	
}











