package kh.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	/*
	 * public List<BoardDTO> boardlist(int cpage) { String sql =
	 * "select * from board"; }
	 */
	
}
