package com.shinhan.SimpleBoard;

import java.util.List;

public class CommonView {
	
	public static void menuDisplay() {
		System.out.println("====================================");
		System.out.println("1. List 2. Write 3. Update 4. Delete");
		System.out.println("====================================");
	}
	
	public static void display(String message) {
		System.out.println("알림 > " + message);
	}
	
	public static void goodBye() {
		System.out.println("=========    ====    =========");
		System.out.println("========  ===    ===  ========");
		System.out.println("=======   Good Bye!!!  =======");
		System.out.println("========  ==========  ========");
		System.out.println("==========  ======  ==========");
		System.out.println("============  ==  ============");
		System.out.println("==============   =============");
	}

	public static void goodBye(String message) {
		System.out.println("=========" + message + " 게시판 종료=========");
	}

	public static void display_ready() {
		display("서비스 준비중입니다.");
	}

	public static void display(List<?> list, String message) {
		if (list.size() == 0) {
			CommonView.display("해당하는 데이터가 존재하지 않습니다.");
			return;
		}
		System.out.println("=====" + message + " 여러건 조회=====");
		list.stream().forEach(dto -> System.out.println(dto));
	}

	public static void display(SimpleBoardDTO dto, String message) {
		if (dto == null) {
			CommonView.display("해당하는 데이터가 존재하지 않습니다.");
			return;
		}
		System.out.println("=====" + message + " 정보 조회=====");
		System.out.println(dto);
	}
}
