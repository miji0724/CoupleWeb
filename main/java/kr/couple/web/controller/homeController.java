package kr.couple.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	
	
	@GetMapping(value = {"/home","/"})
	public String home(Model model) {
		return "/home";
	}
	
//	@GetMapping(value = "/member/bucketList")
//	public String viewbucketList(Model model) {
//		return "member/bucketList";
//	}
//	@GetMapping(value = "/bucketList/bucketList")
//	public String bucketList(Model model) {
//		return "/bucketList/bucketList";
//	}
}
