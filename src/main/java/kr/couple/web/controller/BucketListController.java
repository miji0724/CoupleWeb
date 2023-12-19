package kr.couple.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.couple.web.service.BucketListService;
import kr.couple.web.vo.BucketListCommVO;
import kr.couple.web.vo.BucketListVO;
import kr.couple.web.vo.MemberVO;
import kr.couple.web.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/member")
@Slf4j
public class BucketListController {

	@Autowired
	private BucketListService bucketListService;
	// -----------------------------------------------------------------------------------------
	// 목록보기

	@RequestMapping(value = { "/bucketList", "/" })
	public String getList(@ModelAttribute BucketListCommVO bv, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		if(mvo == null ) {
			return "redirect:/member/login";
		}
		
		Map<String, String> params = new HashMap<String, String>();
		
		String shared_id = mvo.getShared_id();
		
		params.put("shared_id", shared_id);
		
		Paging<BucketListVO> paging = bucketListService.selectList(bv, params);
		Paging<BucketListVO> paging２ = bucketListService.selectList2(bv, params);
		model.addAttribute("pv", paging);
		model.addAttribute("bv", bv);
		model.addAttribute("pvb", paging２);

		return "/bucketList/bucketList";
	}

	// -----------------------------------------------------------------------------------------
	// 저장하기
	// 입력폼
	@GetMapping(value = "/insert")
	public String insert(@ModelAttribute BucketListCommVO bv, HttpServletRequest request, Model model) {
		log.info("insert({},{})리턴", bv, model);
		
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		if(mvo == null ) {
			return "redirect:/member/login";
		}
		String shared_id = mvo.getShared_id();
		
		List<String> member_name_list = new ArrayList<String>();
		
		String member_name1 = mvo.getMember_name1();
		String member_name2 = mvo.getMember_name2();
		
		member_name_list.add(member_name1);
		member_name_list.add(member_name2);
		
		model.addAttribute("shared_id", shared_id);
		model.addAttribute("member_name_list", member_name_list);
		model.addAttribute("bv", bv);
		return "/bucketList/bucketListInsert";
	}

	// 입력내용 저장
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/insertOk")
	public String insertOkGet() {
		return "redirect:/member/";
	}

	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/insertOk")
	public String insertOkPost(@ModelAttribute BucketListCommVO bv, @ModelAttribute BucketListVO vo) {
		// 1. 서비스를 호출하여 저장을 수행한다.
		
		if (vo != null) {
			bucketListService.insert(vo);
		}
		// log.info("insertOkPost({},{})리턴", bv, vo);
		// 2. 1페이지로 이동한다.
		return "redirect:/member/?p=1&s=" + bv.getS() + "&b=" + bv.getB();
	}

	// -----------------------------------------------------------------------------------------
	// 수정하기
	// 입력폼
	@GetMapping(value = "/update")
	public String update(@ModelAttribute BucketListCommVO bv, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		if(mvo == null ) {
			return "redirect:/member/login";
		}
		String shared_id = mvo.getShared_id();
		
		List<String> member_name_list = new ArrayList<String>();
		
		String member_name1 = mvo.getMember_name1();
		String member_name2 = mvo.getMember_name2();
		
		member_name_list.add(member_name1);
		member_name_list.add(member_name2);
		
		model.addAttribute("shared_id", shared_id);
		model.addAttribute("member_name_list", member_name_list);
		
		model.addAttribute("bv", bv);
		model.addAttribute("bucketList", bucketListService.selectById(bv.getId()));

		return "/bucketList/update";
	}

	// 수정내용 저장
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/updateOk")
	public String updateOkGet() {
		return "redirect:/member/";
	}

	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/updateOk")
	public String updateOkPost(@ModelAttribute BucketListCommVO bv, @ModelAttribute BucketListVO vo) {
		
		log.info("peaceLog bv : {}", bv.toString());
		log.info("peaceLog vo : {}", vo.toString());
		// 1. 서비스를 호출하여 수정을 수행한다.
		if (vo != null) {
			bucketListService.update(vo);
		}
		// 2. 1페이지로 이동한다.
		return "redirect:/member/view?id=" + bv.getId() + "&p=" + bv.getP() + "&s=" + bv.getS() + "&b=" + bv.getB();
	}

	// -----------------------------------------------------------------------------------------
	// 삭제하기
	// 삭제폼
	@GetMapping(value = "/delete")
	public String delete(@ModelAttribute BucketListCommVO bv, Model model) {
		model.addAttribute("bv", bv);
		model.addAttribute("bucketList", bucketListService.selectById(bv.getId()));
		return "bucketList/delete";
	}

	// 내용 삭제
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/deleteOk")
	public String deleteOkGet() {
		return "redirect:/member/";
	}

	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/deleteOk")
	public String deleteOkPost(@ModelAttribute BucketListCommVO bv, @ModelAttribute BucketListVO vo) {
		// 1. 서비스를 호출하여 삭제를 수행한다.
		if (vo != null) {
			bucketListService.delete(vo);
		}
		// 2. 1페이지로 이동한다.
		return "redirect:/member/?p=" + bv.getP() + "&s=" + bv.getS() + "&b=" + bv.getB();
	}

	// -----------------------------------------------------------------------------------------
	// 내용보기
	@GetMapping(value = "/view")
	public String view(@ModelAttribute BucketListCommVO bv, Model model) {
		BucketListVO bucketListVO = bucketListService.view(bv.getId());
		if (bucketListVO != null) {
			model.addAttribute("bv", bv);
			model.addAttribute("vo", bucketListVO);
			// 줄바꿈 처리를 위해 두개의 변수를 추가한다.
			model.addAttribute("br", "<br>");
			model.addAttribute("newLine", "\n");
			return "/bucketList/bucketListView";
		} else {
			return "redirect:/member/";
		}
	}

}
