package kh.spring.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;

	@Autowired
	private HttpSession session;
	
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}
	
	public int idDuplCheck(String id) {
		return dao.idDuplCheck(id);
	}
	
	public int login(String id,String pw) {
		return dao.login(id, pw);
	}
	
	public MemberDTO mypage(String id) {
		return dao.mypage(id);
	}
	
	public int dropMember(String id) {
		return dao.dropMember(id);
	}

}
