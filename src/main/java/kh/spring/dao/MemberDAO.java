package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	public MemberDTO mypage(String id) {
		String sql = "select * from member where id=?";
		
		return jdbc.queryForObject(sql, new RowMapper<MemberDTO>() {
			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));				
				dto.setAddress2(rs.getString("address2"));	
				dto.setReg_date(rs.getDate("reg_date"));
				System.out.println(dto);
				return dto;
				
			}
		},id);

	}
	
	public int dropMember(String id) {
		System.out.println(id);
		String sql = "delete from member where id=?";
		return jdbc.update(sql,id);
	}
	
}
