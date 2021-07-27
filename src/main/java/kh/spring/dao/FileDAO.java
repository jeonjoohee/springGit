
package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.FileDTO;

@Repository
public class FileDAO {
   
   @Autowired
   private JdbcTemplate jdbc;

   public int fileInsert(FileDTO dto) {
      String sql = "insert into files values(files_seq.nextval,?,?,sysdate,?)";
      return jdbc.update(sql,dto.getOriName(),dto.getSysName(),dto.getBoard_seq());
      
   }
   
   public List<FileDTO> allFiles(int seq){
      String sql ="select * from files where board_seq=?";
      return jdbc.query(sql, new RowMapper<FileDTO>() {
         @Override
         public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileDTO dto = new FileDTO();
            dto.setSeq(rs.getInt("seq"));
            dto.setOriName(rs.getString("oriName"));
            dto.setSysName(rs.getString("sysName"));
            dto.setFile_date(rs.getDate("file_date"));
            dto.setBoard_seq(rs.getInt("Board_seq"));
            return dto;
         }
      }, seq);
   }

}