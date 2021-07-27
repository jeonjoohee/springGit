package kh.spring.dto;

public class FilesDTO {
	private int seq;
	private String oriName;
	private String sysName;
	private String reg_date;
	private int parent;
	
	public FilesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilesDTO(int seq, String oriName, String sysName, String reg_date, int parent) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.reg_date = reg_date;
		this.parent = parent;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	

	}
