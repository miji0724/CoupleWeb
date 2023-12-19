package kr.couple.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.couple.web.service.BucketListService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/bucketApi")
@Slf4j
public class BucketListApiController {

	@Autowired
	private BucketListService bucketListService;
	
	// 완료처리
	@PatchMapping(value = "/updateComcheck")
	@ResponseBody
	public int updateComcheck(@RequestBody Map<String,String> reqbody){
		log.info("peaceLog updateComcheck reqbody : ", reqbody);
		
		return bucketListService.updateComcheck(reqbody);
	}
	
}
