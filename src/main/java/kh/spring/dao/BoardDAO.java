package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.MemberDTO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;

	public int write(BoardDTO dto) {
		String sql = "insert into board values (board_seq.nextval, ?, ?, ?, sysdate, 0)";
		return jdbc.update(sql, dto.getTitle(), dto.getContents(), dto.getWriter());
	}

	public List<BoardDTO> view(int seq) {
		String sql = "select * from board where seq=?";
		return jdbc.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWriter(rs.getString("writer"));
				return dto;
			}
		}, Integer.class, seq);
	}

	/*
	 * public List<BoardDTO> boardlist(int cpage) { String sql =
	 * "select * from board"; }
	 */
	
	public int delete(int seq) {
		String sql = "delete board where seq=?";
		return jdbc.update(sql,seq);
	}
	public int modify(BoardDTO dto) {
		String sql = "update board set title=?, contents=?, write_date=sysdate where seq=?";
		return jdbc.queryForObject(sql, Integer.class, dto.getTitle(),dto.getContents(),dto.getSeq());
	}
}
