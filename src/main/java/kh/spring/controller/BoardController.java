package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;


@Controller
@RequestMapping("/bod")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("boardWrite")
	public String boardWrite(BoardDTO dto) {
		String writer = (String)session.getAttribute("login");
		session.setAttribute("login", writer);
		
		int result = dao.boardWrite(dto);
		return "redirect:boardlist";
	}
	
	@RequestMapping("BoardView")
	public String BoardView(int seq, Model model){
		BoardDTO dto = dao.boardView(seq);
		model.addAttribute("dto", dto);
		return "view";
	}

	
	/*
	 * @RequestMapping("/boardlist") public List<BoardDTO> boardlist(int cpage) {
	 * System.out.println("요청페이지 : " + cpage); List<BoardDTO> list =
	 * dao.boardlist(cpage); }
	 */

}