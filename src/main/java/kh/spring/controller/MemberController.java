package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

//	@Autowired
//	private MemberDAO dao;
	
	@Autowired
	private MemberService mservice;

	@Autowired
	private HttpSession session;

	@RequestMapping("signup")
	public String signup() {
		return "member/signup";
	}

	@RequestMapping("signupProc")
	public String signupProc(MemberDTO dto){
		mservice.insert(dto);
		return "redirect:/";
	}
	@RequestMapping("loginProc")
	public String loginProc(String id, String pw){
		int result = mservice.login(id, pw);

		if(result > 0) {
			session.setAttribute("login", id);
		}

		return "redirect:/";
	}
	@RequestMapping("mypage")
	public String mypage(){
		System.out.println("마페");
		String id = (String) session.getAttribute("login");
		MemberDTO dto = mservice.mypage(id);

		session.setAttribute("mydto", dto);
		//session.getId();		
		return "member/mypage";
	}
	@RequestMapping("logout")
	public String logout(){
		System.out.println("로그아웃");
		session.invalidate();
		return "redirect:/";
	}
	@RequestMapping("dropmember")
	public String dropmember(){
		System.out.println("탈퇴");
		String id = (String) session.getAttribute("login");
		
		mservice.dropMember(id);
		session.invalidate();
		
		return "redirect:/";
	}
	
	

	@ResponseBody
	@RequestMapping("duplCheck")
	public String duplCheck(String id)  {
		System.out.println(id);

		int result = mservice.idDuplCheck(id);

		return String.valueOf(result);
	}

}
