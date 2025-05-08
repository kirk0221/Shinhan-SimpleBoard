package com.shinhan.SimpleBoard;

public class SimpleBoardService {
	SimpleBoardDAO simpleboardDAO = new SimpleBoardDAO();
	
	public int insertPost(SimpleBoardDTO sb) {
		return simpleboardDAO.insertPost(sb);
	}
	
}
