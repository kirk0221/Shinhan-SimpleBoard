package com.shinhan.SimpleBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.utils.DBUtil;

public class SimpleBoardDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	
	public int insertPost(SimpleBoardDTO sb) {
		int result = 0;
		conn = DBUtil.getConnection();
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
			st = conn.prepareStatement(sql);
			
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
}
