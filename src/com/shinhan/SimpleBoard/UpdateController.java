package com.shinhan.SimpleBoard;

import com.shinhan.utils.InsertUtil;
import com.shinhan.utils.ScannerUtil;

public class UpdateController implements CommonControllerInterface {
	SimpleBoardService boardService = new SimpleBoardService();

	public void execute() {
		boolean isStop = false;
		while(!isStop) {
			SimpleBoardView.manuDisplay();
			int job = ScannerUtil.sc.nextInt();
			switch(job) {
			case 1->{f_update();}
			case 2->{isStop = true;}
			}
		}
	}
	
	private void f_update() {
		String writer = InsertUtil.check_String_Input("본인의 이름을 입력하세요..>>>");
		int bno = InsertUtil.check_Integer_Input("수정하고 싶은 게시글 번호를 입력하세요..>>> ");
		String title = InsertUtil.check_String_Input("수정하고 싶은 게시글의 제목을 입력하세요..>>>");
		
//		// 이름, 게시글, 제목 검증
		SimpleBoardDTO chack = boardService.selectBoard(writer, bno, title);
		if(chack != null) {
			System.out.print(writer + "님이 작성하신 게시글이 존재합니다. 수정할 정보를 입력하세요..>>>");
			SimpleBoardDTO board = makeBoard();
			board.setBno(chack.getBno());
			
			int result = boardService.boardUpdate(board);
			if(result > 0) {
				SimpleBoardView.display("성공적으로 게시글이 수정되었습니다.");
			}else {
				SimpleBoardView.display("게시글 수정에 실패했습니다..");
			}
		}else {
			SimpleBoardView.display("해당 게시글이 존재하지 않습니다.");
		}
		
	}
	

	private SimpleBoardDTO makeBoard() {
		String writer = InsertUtil.check_String_Input("이름 : " );
		String title = InsertUtil.check_String_Input("제목 : " );
		String contents = InsertUtil.check_String_Input("내용 : " );
		
		SimpleBoardDTO board = SimpleBoardDTO.builder()
				.writer(writer)
				.writedate(null)
				.title(title)
				.contents(contents)
				.build();
		
		return board;
	}

}

