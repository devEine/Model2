package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//인터페이스 -> 추상메서드, 상수 사용 가능 
	//상속을 통해서 추상메서드를 오버라이딩하여 사용(강제성)
	
	// /+ ** + enter
	
	/**
	 * 추상 메서드이며, 반드시 오버라이딩해서 사용해야 함 
	 * 실행할 때, req와 resp정보를 매개변수로 전달해야지만 호출 가능 
	 * 호출이 완료되면 ActionForward(주소,방식이 담긴 객체)라는 정보를 리턴함 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
	throws Exception; //Exception에 마우스 커서 대고 f3누르기 -> java파일 - jdk1.8 - src.zip 
	//메서드의 존재 이유 -> ActionForward 
	
}
