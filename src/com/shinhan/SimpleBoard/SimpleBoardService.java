package com.shinhan.SimpleBoard;

public class SimpleBoardService {
	SimpleBoardDAO boardDAO = new SimpleBoardDAO();
	
	public int boardUpdate(SimpleBoardDTO board) {
		int result = boardDAO.boardUpdate(board);
		if(result == 0) {
			System.out.println("수정에 실패하셨습니다.");
		}
		return result;
	}
	
	public SimpleBoardDTO selectBoard(String writer, int bno, String title) {
		SimpleBoardDTO board = boardDAO.selectBoard(writer, bno, title);
		if(board != null) {
			
		}else {
			
		}
		return board;
	}
}
