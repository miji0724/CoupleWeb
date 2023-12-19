package kr.couple.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.couple.web.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	private TestDAO testDAO;
	
	public Date selectToday() {
		return testDAO.selectToday();
	}
	
}

