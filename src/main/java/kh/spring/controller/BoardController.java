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
		int result = dao.boardWrite(dto);
		return "redirect:bod/boardlist?cpage=1";
	}
	
	@RequestMapping("boardView")
	public String boardView(int seq, Model model){
		BoardDTO dto = dao.boardView(seq);
		dao.view_countPlus(seq);
		model.addAttribute("dto", dto);
		return "boardView";
	}

	
	/*
	 * @RequestMapping("/boardlist") public List<BoardDTO> boardlist(int cpage) {
	 * System.out.println("요청페이지 : " + cpage); List<BoardDTO> list =
	 * dao.boardlist(cpage); }
	 */
	@RequestMapping("deleteProc")
	public String deleteProc(int seq){
		dao.delete(seq);
		return "board/boardList?cpage=1";
	}
	
	@RequestMapping("boardModify")
	public String boardModify(int seq) {
		BoardDTO dto = dao.boardView(seq);
		session.setAttribute("dto", dto);
		return "board/Modify";
	}
	
	@RequestMapping("modifyProc")
	public String modifyProc(BoardDTO dto) {
		dao.modify(dto);
		return "redirect:board/boardView";
	}

}