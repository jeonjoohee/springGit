package kh.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(MemberDTO dto) {
		String sql = "insert into member values (?,?,?,?,?,?,?,?,sysdate)";
		return jdbc.update(sql, dto.getId(), dto.getPw(), dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getZipcode(), dto.getAddress1(), dto.getAddress2());
	}

	public int idDuplCheck(String id) {
		String sql = "select count(*) from member where id=?";
		return jdbc.queryForObject(sql, Integer.class,id);

	}
	public int login(String id,String pw) {
		String sql = "select count(*) from member where id=? and pw=?";
		
		return jdbc.queryForObject(sql, Integer.class,id,pw);
	}
	public int dropMember(String id) {
		System.out.println(id);
		String sql = "delete from member where id=?";
		return jdbc.update(sql,id);
	}
	
}
