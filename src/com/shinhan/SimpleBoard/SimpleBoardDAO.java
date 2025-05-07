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
		int resultCount;
		
		static final String BOARD_UPDATE = """
				update simpleboard
				set
				writer = ?,title = ?, contents = ?
				where bno =? and writer = ? and title =?
				""";
		static final String BOARD_SELECT = """
				select *
				from simpleboard
				where bno =? and writer = ? and title =?
				""";
	
	public int boardUpdate(SimpleBoardDTO board) {
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(BOARD_UPDATE);
			pst.setString(1, board.getWriter());
			pst.setString(2, board.getTitle());
			pst.setString(3, board.getContents());
			pst.setInt(4, board.getBno());
			pst.setString(5, board.getWriter());
			pst.setString(6, board.getTitle());
		} catch (SQLException e) {
			e.printStackTrace();
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
		return null;
	}
	
}

