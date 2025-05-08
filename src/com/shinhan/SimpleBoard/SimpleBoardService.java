package com.shinhan.SimpleBoard;

import java.util.List;

public class SimpleBoardService {
	SimpleBoardDAO simpleBoardDAO = new SimpleBoardDAO();
	public List<SimpleBoardDTO> selectAll(){
		return simpleBoardDAO.selectAll();
	}
	public List<SimpleBoardDTO> selectByWriter(String writer){
		return simpleBoardDAO.selectByWriter(writer);
	}
	public List<SimpleBoardDTO> selectByTitle(String title){
		return simpleBoardDAO.selectByTitle(title);
	}
	public List<SimpleBoardDTO> selectByContents(String contents){
		return simpleBoardDAO.selectByContents(contents);
	}
	public List<SimpleBoardDTO> selectByTitleAndContents(String writer, String contents){
		return simpleBoardDAO.selectByTitleAndContents(writer, contents);
	}
	public boolean deleteByNum(int num) {
		// TODO Auto-generated method stub
		return simpleBoardDAO.deleteBoard(num);
	}
  	public int boardUpdate(SimpleBoardDTO board) {
		int result = boardDAO.boardUpdate(board);
		if(result == 0) {
			System.out.println("수정에 실패하셨습니다.");
		}
		return result;
	}
	public SimpleBoardDTO selectBoard(String writer, int bno, String title) {
		SimpleBoardDTO board = boardDAO.selectBoard(writer, bno, title);
		return board;
	public int insertPost(SimpleBoardDTO sb) {
		return simpleboardDAO.insertPost(sb);
	}
}
