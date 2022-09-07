package com.itwillbs.board.action;

public class ActionForward {
	//페이지 이동을 위한 정보를 저장하는 객체 
	
	private String path; //이동할 주소 
	private boolean isRedirect; //이동할 방식
	
	//isRedirect
	//true - sendRedirect() 방식으로 이동
	//false - forward 방식으로 이동
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	} //~is 붙은 변수명은 get메서드가 다른 형식으로 생성됨
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}


}
