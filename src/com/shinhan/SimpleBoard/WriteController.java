package com.shinhan.SimpleBoard;

import java.util.Scanner;

import com.shinhan.utils.InsertUtil;

public class WriteController implements CommonControllerInterface{
	
	static Scanner sc = new Scanner(System.in);
	static SimpleBoardService simpleboardService = new SimpleBoardService();
	
	@Override
	public void execute() {
		boolean isStop = false;
		while(!isStop) {
			menuDisplay();
			int job = sc.nextInt();
			switch (job) {
			case 1 -> {
				f_insertSimpleBoard();
			}
			case 99 -> {isStop = true;}
			}
		}
		System.out.println("===종료합니다.===");
		
		
	}

	private static void f_insertSimpleBoard() {
		String writer = InsertUtil.check_String_Input("작성자 입력 >>");
		
		String title = InsertUtil.check_String_Input("제목 입력 >>");
		
		String contents = InsertUtil.check_String_Input("내용 입력 >>");
		
		SimpleBoardDTO sb = SimpleBoardDTO.builder().writer(writer).title(title).contents(contents).build();
		
		int result = simpleboardService.insertPost(sb);
		if(result>0) {
			System.out.println("게시글이 성공적으로 등록되었습니다.");
		} else {
			System.out.println("게시글 등록에 실패했습니다.");
		}
		
	}

	private void menuDisplay() {
		System.out.println("-------------------------");
		System.out.println("1.insert 99. 끝");
		System.out.println("-------------------------");
		System.out.print("작업선택>");
		
	}
	
	

}
