package kh.spring.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public void upload(String realPath, FilesDTO fdto, MultipartFile[] file) throws Exception{
		File filesPath = new File(realPath);
		if(!filesPath.exists()) {filesPath.mkdir();}

		for(MultipartFile tmp : file) {
			if(tmp.getSize() > 0) {
				String oriName = tmp.getOriginalFilename();
				String sysName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+oriName;

				System.out.println(filesPath.getAbsolutePath());
				fdto.setOriName(oriName);
				fdto.setSysName(sysName);
				tmp.transferTo(new File(filesPath.getAbsolutePath()+"/"+sysName));

				fdao.fileInsert(fdto);
			}
		}
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
