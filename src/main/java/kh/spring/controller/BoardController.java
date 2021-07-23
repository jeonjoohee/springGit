package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.config.BoardConfig;
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
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("writeProc")
	public String writeProc(BoardDTO dto) {
		String writer = (String)session.getAttribute("login");	
		dto.setWriter(writer);
		int result = dao.boardWrite(dto);
		return "redirect:/bod/boardlist?cpage=1";
	}
	
	@RequestMapping("viewProc")
	public String viewProc(int seq, Model model){
		BoardDTO dto = dao.boardView(seq);
		//dao.view_countPlus(seq);
		model.addAttribute("dto", dto);
		return "board/boardView";
	}

	
	@RequestMapping("deleteProc")
	public String deleteProc(int seq){
		dao.delete(seq);
		return "bod/boardlist?cpage=1";
	}
	
	@RequestMapping("boardModify")
	public String boardModify(int seq) {
		BoardDTO dto = dao.boardView(seq);
		session.setAttribute("dto", dto);
		return "board/boardModify";
	}
	
	@RequestMapping("modifyProc")
	public String modifyProc(BoardDTO dto) {
		dao.modify(dto);
		return "board/boardView";
	}
	
	@RequestMapping("boardlist") 
	public String boardlist(int cpage, String key, String word, Model model) {
		System.out.println("요청페이지 : " + cpage);

		int startNum = ((cpage-1) * BoardConfig.RECORD_COUNT_PER_PAGE) + 1;
		int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;

		List<BoardDTO> list = dao.boardlist(startNum, endNum); 
		List<String> navi = dao.navi(cpage);

		model.addAttribute("list", list);
		model.addAttribute("navis", navi);

		return "board/boardList";
	}
	
	@ExceptionHandler
	   public String ExceptionHandler(Exception e) {
	      e.printStackTrace();
	      return "error";
	   }

}