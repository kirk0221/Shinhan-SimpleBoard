package com.shinhan.SimpleBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shinhan.utils.DBUtil;

public class SimpleBoardDAO {
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		
		
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
	
	public int boardUpdate(SimpleBoardDTO board) {
		conn = DBUtil.getConnection();
		int resultCount=0;
		try {
			pst = conn.prepareStatement(BOARD_UPDATE);
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
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(BOARD_SELECT);
			pst.setString(1, writer);
			pst.setInt(2, bno);
			pst.setString(3, title);
			rs = pst.executeQuery();
			while(rs.next()) {
				board = makeboard(rs);
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

	private SimpleBoardDTO makeboard(ResultSet rs) throws SQLException {
		SimpleBoardDTO board = SimpleBoardDTO.builder()
				.bno(rs.getInt(1))
				.writer(rs.getString(2))
				.writedate(rs.getDate(3))
				.title(rs.getString(4))
				.contents(rs.getString(5))
				.build();
		return board;
	}
	
}

