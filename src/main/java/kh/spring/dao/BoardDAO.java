package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.config.BoardConfig;
import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;

	public int boardWrite(BoardDTO dto) {
		String sql = "insert into board values (?, ?, ?, ?, sysdate, 0)";
		return jdbc.update(sql,dto.getSeq(), dto.getTitle(), dto.getContents(), dto.getWriter());
	}
//	public int boardWrite(BoardDTO dto) {
//		String sql = "insert into board values (board_seq.nextval, ?, ?, ?, sysdate, 0)";
//		return jdbc.update(sql, dto.getTitle(), dto.getContents(), dto.getWriter());
//	}
	
	public int seqNextval() {
	      String sql ="select board_seq.nextval from dual";
	      return jdbc.queryForObject(sql, Integer.class);
	   }

	public BoardDTO boardView(int seq) {
		String sql = "select * from board where seq=?";
		return jdbc.queryForObject(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWriter(rs.getString("writer"));
				dto.setWrite_date(rs.getDate("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		}, seq);
	}
	
//	private int view_countSearch(int seq) {
//		String sql = "select view_count from board where seq=?";
//		return jdbc.queryForObject(sql, Integer.class, seq);
//	}
//	
//	public int view_countPlus(int seq) {
//		BoardDAO dao = new BoardDAO();
//		int view_count = dao.view_countSearch(seq);
//		String sql = "update board set view_count=? where seq=?";
//		return jdbc.update(sql, view_count+1, seq);
//	}
	
	public int delete(int seq) {
		String sql = "delete board where seq=?";
		return jdbc.update(sql,seq);
	}
	public int modify(BoardDTO dto) {
		String sql = "update board set title=?, contents=?, write_date=sysdate where seq=?";
		return jdbc.update(sql, dto.getTitle(),dto.getContents(),dto.getSeq());
	}
	
	public List<BoardDTO> boardlist(int startNum, int endNum) { 
		String sql = "select * from (select row_number() over (order by seq desc) as rnum, "
				+ "seq, title, contents, writer, write_date, view_count from board) where rnum between ? and ?"; 
		return jdbc.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWriter(rs.getString("writer"));
				dto.setWrite_date(rs.getDate("write_date"));
				dto.setView_count(rs.getInt("view_count"));

				return dto;
			}
		},startNum,endNum);
	}

	public int getTotalCount() {
		String sq1 = "select count(*) from board";
		return jdbc.queryForObject(sq1, Integer.class);
	}

	public List<String> navi(int currentPage) {
		int totalCount = this.getTotalCount(); // 총 글의 수
		int pagewriteCount = BoardConfig.RECORD_COUNT_PER_PAGE;
		int pageNaviCount = BoardConfig.NAVI_COUNT_PER_PAGE;

		int pageTotalCount = 0; // 몇개의 페이지가 필요한가 -> 페이지의 총개수
		if(totalCount % pagewriteCount > 0) {
			pageTotalCount = totalCount / pagewriteCount + 1; // 나머지가있으면 한페이지가 더 있어야됨
		}else {
			pageTotalCount = totalCount / pagewriteCount;
		}

		if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}else if(currentPage < 1) {
			currentPage = 1;
		}

		int startNavi = (((currentPage - 1) / pageNaviCount) * pageNaviCount) + 1;

		int endNavi = startNavi + (pageNaviCount -1);
		if(endNavi > pageTotalCount) { endNavi = pageTotalCount; }

		boolean needPrev = false;
		boolean needNext = false;
		if(startNavi==1) {needNext = true;}
		if(endNavi==pageTotalCount) {needPrev = true;}

		List<String> pageNavi = new ArrayList<>(); // 전체 페이지의 수를 담는 배열

		if(needPrev) {pageNavi.add("<");} 

		for(int i=startNavi; i<=endNavi; i++) {
			pageNavi.add(String.valueOf(i));
		}

		if(needNext) {pageNavi.add(">"); }

		return pageNavi;
	}
}
