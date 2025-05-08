package com.shinhan.SimpleBoard;

import java.util.Scanner;

import com.shinhan.utils.InsertUtil;

public class DeleteController implements CommonControllerInterface {
	
	SimpleBoardService simpleBoardService = new SimpleBoardService();
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		deleteAll();
	}
	private void deleteAll() {
		// TODO Auto-generated method stub
		int num = InsertUtil.check_Integer_Input("삭제할 글 번호 입력 > ");
		CommonView.display(simpleBoardService.deleteByNum(num));
	}

}
