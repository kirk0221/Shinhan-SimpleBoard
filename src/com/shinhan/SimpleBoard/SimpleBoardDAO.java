package com.shinhan.SimpleBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.utils.DBUtil;

public class SimpleBoardDAO {
	public final String READ_ALL = "select * from SimpleBoard";
	public final String READ_BY_WRITER = "select * from SimpleBoard where writer like ?";
	public final String READ_BY_TITLE = "select * from SimpleBoard where title like ?";
	public final String READ_BY_CONTENTS = "select * from SimpleBoard where contents like ?";
	public final String READ_BY_TITLE_AND_CONTENTS = "select * from SimpleBoard where title like ? and contents like ?";
	public final String DELETE= "delete from SimpleBoard where bno = ?";
	
	public boolean deleteBoard(int bno) {
		
	    boolean result = false;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement(DELETE);
	        pstmt.setInt(1, bno);
	        
	        pstmt.executeUpdate();
	        
	        conn.commit();
	        result=true;
	    } catch (SQLException e) {
	    	try {
				if (conn != null)
					conn.rollback(); 
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			e.printStackTrace();
	    } finally {
	    	DBUtil.dbDisconnect(conn, pstmt, rs);
	    	
	    }
	    
	    return result;
	}
	
	
	public List<SimpleBoardDTO> selectByTitleAndContents(String writer, String contents){
		List<SimpleBoardDTO> simpleBoardList = new ArrayList<SimpleBoardDTO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(READ_BY_TITLE_AND_CONTENTS);
			st.setString(1, "%" + writer + "%");
			st.setString(2, "%" + contents + "%");
			rs = st.executeQuery();
			while(rs.next()) {
				SimpleBoardDTO simpleBoardDTO = makesimpleBoardDTO(rs);
				simpleBoardList.add(simpleBoardDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
			
		}
		return simpleBoardList;
	}
	
	public List<SimpleBoardDTO> selectByContents(String contents){
		List<SimpleBoardDTO> simpleBoardList = new ArrayList<SimpleBoardDTO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(READ_BY_CONTENTS);
			st.setString(1, "%" + contents + "%");
			rs = st.executeQuery();
			while(rs.next()) {
				SimpleBoardDTO simpleBoardDTO = makesimpleBoardDTO(rs);
				simpleBoardList.add(simpleBoardDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return simpleBoardList;
	}
	
	public List<SimpleBoardDTO> selectByTitle(String title){
		List<SimpleBoardDTO> simpleBoardList = new ArrayList<SimpleBoardDTO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(READ_BY_TITLE);
			st.setString(1, "%" + title + "%");
			rs = st.executeQuery();
			while(rs.next()) {
				SimpleBoardDTO simpleBoardDTO = makesimpleBoardDTO(rs);
				simpleBoardList.add(simpleBoardDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return simpleBoardList;
	}
	
	public List<SimpleBoardDTO> selectByWriter(String writer){
		List<SimpleBoardDTO> simpleBoardList = new ArrayList<SimpleBoardDTO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(READ_BY_WRITER);
			st.setString(1, "%" + writer + "%");
			rs = st.executeQuery();
			while(rs.next()) {
				SimpleBoardDTO simpleBoardDTO = makesimpleBoardDTO(rs);
				simpleBoardList.add(simpleBoardDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return simpleBoardList;
	}
	
	public List<SimpleBoardDTO> selectAll(){
		List<SimpleBoardDTO> simpleBoardList = new ArrayList<SimpleBoardDTO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(READ_ALL);
			while(rs.next()) {
				SimpleBoardDTO simpleBoardDTO = makesimpleBoardDTO(rs);
				simpleBoardList.add(simpleBoardDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return simpleBoardList;
	}

	private SimpleBoardDTO makesimpleBoardDTO(ResultSet rs) throws SQLException {
		SimpleBoardDTO simpleBoardDTO = SimpleBoardDTO.builder()
				.bno(rs.getInt("bno"))
				.writer(rs.getString("writer"))
				.writedate(rs.getDate("writedate"))
				.title(rs.getString("title"))
				.contents(rs.getString("contents"))
				.build();
		return simpleBoardDTO;
	}
	
}
