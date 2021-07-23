package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.BoardDAO;

@Controller
@RequestMapping("/bod")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;

	


}