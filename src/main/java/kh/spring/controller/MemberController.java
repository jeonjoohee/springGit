package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.MemberDAO;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberDAO dao;

	@Autowired
	private HttpSession session;

	@RequestMapping("signup")
	public String signup() {
		return "member/signup";
	}


}
