package com.shinhan.SimpleBoard;

import com.shinhan.utils.ScannerUtil;

public class UpdateController implements CommonControllerInterface {
	ScannerUtil scUtil = new ScannerUtil();
	SimpleBoardService boardService = new SimpleBoardService();

	public void execute() {
		boolean isStop = false;
		while(!isStop) {
			SimpleView.manuDisplay();
			int job = scUtil.sc.nextInt();
			switch(job) {
			case 1->{f_update();}
			case 2->{isStop = true;}
			}
		}
	}

	private void f_update() {
		System.out.print("본인의 이름을 입력하세요..>>>");
		String writer = scUtil.sc.next();
		System.out.print("수정하고 싶은 게시글 번호를 입력하세요..>>>");
		int bno = scUtil.sc.nextInt();
		System.out.print("수정하고 싶은 게시글의 제목을 입력하세요..>>>");
		String title = scUtil.sc.next();
		
		// 이름, 게시글, 제목 검증
		SimpleBoardDTO chack = boardService.selectBoard(writer, bno, title);
		if(chack != null) {
			System.out.print(writer + "님이 작성하신 게시글이 존재합니다. 수정할 정보를 입력하세요..>>>");
			SimpleBoardDTO board = makeBoard();
			board.setBno(chack.getBno());
			
			int result = boardService.boardUpdate(board);
			if(result > 0) {
				SimpleView.display("성공적으로 게시글이 수정되었습니다.");
			}else {
				SimpleView.display("게시글 수정에 실패했습니다..");
			}
		}else {
			SimpleView.display("일치하는 정보가 없습니다.");
		}
		
	}

	private SimpleBoardDTO makeBoard() {
		System.out.print("이름 : " );
		String writer = scUtil.sc.next();
		System.out.print("제목 : " );
		String title = scUtil.sc.next();
		System.out.print("내용 : " );
		String contents = scUtil.sc.next();
		SimpleBoardDTO board = SimpleBoardDTO.builder()
				.writer(writer)
				.writedate(null)
				.title(title)
				.contents(contents)
				.build();
		
		return board;
	}

}

