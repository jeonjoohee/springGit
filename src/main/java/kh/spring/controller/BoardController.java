package kh.spring.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.service.BoardService;
import kh.spring.service.FilesService;


@Controller
@RequestMapping("/bod")
public class BoardController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private BoardService bservice;
	
	@Autowired
	private FilesService fservice;
	
	@RequestMapping("boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}

	@RequestMapping("writeProc")
	public String writeProc(String title, String contents, FilesDTO fdto, MultipartFile[] file) throws Exception{
		// 글쓰는거 
		String writer = (String)session.getAttribute("login");   
		int seq = bservice.seqNextval();
		System.out.println(title);
		int result = bservice.boardWrite(new BoardDTO(seq,title,contents,writer));

		// 파일도 저장하는거 
		String realPath = session.getServletContext().getRealPath("files");
		fdto.setParent(seq);
		
		fservice.upload(realPath,fdto,file);
		return "redirect:/bod/boardlist?cpage=1";
	}

	@RequestMapping("viewProc")
	public String viewProc(int seq, Model model){
		BoardDTO dto = bservice.boardView(seq);
		//dao.view_countPlus(seq);
		model.addAttribute("dto", dto);

		List<FilesDTO> fdto = fservice.fileslist(seq);
		model.addAttribute("fdto", fdto);
		return "board/boardView";
	}


	@RequestMapping("deleteProc")
	public String deleteProc(int seq){
		bservice.delete(seq);
		return "redirect:/bod/boardlist?cpage=1";
	}

	@RequestMapping("boardModify")
	public String boardModify(int seq) {
		BoardDTO dto = bservice.boardView(seq);
		session.setAttribute("dto", dto);
		return "board/boardModify";
	}

	@RequestMapping("modifyProc")
	public String modifyProc(BoardDTO dto) {
		bservice.modify(dto);
		int seq = dto.getSeq();
		return "redirect:/bod/viewProc?seq="+seq;
	}

	@RequestMapping("boardlist") 
	public String boardlist(int cpage, String key, String word, Model model) {
		System.out.println("요청페이지 : " + cpage);

		List<BoardDTO> list = bservice.boardlist(cpage); 
		List<String> navi = bservice.navi(cpage);

		model.addAttribute("list", list);
		model.addAttribute("navis", navi);

		return "board/boardList";
	}

	@RequestMapping("download")
	public void download(String oriName, String sysName, HttpServletResponse resp) throws Exception{
		System.out.println("파일다운");
		String filesPath = session.getServletContext().getRealPath("files");
		fservice.download(filesPath, oriName, sysName, resp);
	}


	@ExceptionHandler
	public String ExceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}

}