package kh.spring.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.FilesDAO;
import kh.spring.dto.FilesDTO;

@Service
public class FilesService {

	@Autowired
	private FilesDAO fdao;

	public int fileInsert(FilesDTO dto) {
		return fdao.fileInsert(dto);
	}

	public List<FilesDTO> fileslist(int parent){
		return fdao.fileslist(parent);
	}



	public void download(String filesPath, String oriName, String sysName, HttpServletResponse resp) throws Exception{
		File targetFile = new File(filesPath + "/" + sysName);

		oriName = new String(oriName.getBytes(),"ISO_8859_1");
		resp.setContentType("application/octet-stream; charset=utf8");
		resp.setHeader("content-Disposition", "attachment;filename=\"" + oriName + "\"");
		try(ServletOutputStream sos = resp.getOutputStream();){
			FileUtils.copyFile(targetFile, sos);
			sos.flush();
		}
	}
}
