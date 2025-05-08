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
	static final String BOARD_UPDATE = """
			update SimpleBoard
			set
			writer = ?,title = ?, contents = ?
			where bno =?
			""";
	static final String BOARD_SELECT = """
			select *
			from SimpleBoard
			where writer = ? and bno = ? and title = ?
			""";
	public int insertPost(SimpleBoardDTO sb) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;
		
		String sql = """
				insert into SimpleBoard(
				    bno,
					writer,
					writedate,
					title,
					contents
					)
				values( bno.nextval,?,sysdate,?,?)
				""";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, sb.getWriter());
			pst.setString(2, sb.getTitle());
			pst.setString(3, sb.getContents());
			result = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			DBUtil.dbDisconnect(conn, pst, null);
		}
		
		return result;
	}
	
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

	
	public int boardUpdate(SimpleBoardDTO board) {
		Connection conn = DBUtil.getConnection();
		int resultCount=0;
		try {
			PreparedStatement pst = conn.prepareStatement(BOARD_UPDATE);
			pst.setString(1, board.getWriter());
			pst.setString(2, board.getTitle());
			pst.setString(3, board.getContents());
			pst.setInt(4, board.getBno());
			resultCount = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return resultCount;

	}
	
	public SimpleBoardDTO selectBoard(String writer, int bno, String title) {
		SimpleBoardDTO board = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(BOARD_SELECT);
			pst.setString(1, writer);
			pst.setInt(2, bno);
			pst.setString(3, title);
			rs = pst.executeQuery();
			while(rs.next()) {
				board = makesimpleBoardDTO(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("==========================");
			System.err.println("일치하는 정보가 존재하지 않습니다.");
			System.err.println("==========================");
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return board;
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

