package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardWriteAction implements Action{
	//오버라이딩 단축키 : alt+shift+s+v
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("M(model):BoardWriteAction.execute()호출");
		//한글처리 
		req.setCharacterEncoding("UTF-8");
		//전달정보 저장(제목,비밀번호,이름,내용)
		//BoardDTO 객체 생성 
		BoardDTO dto = new BoardDTO();
		dto.setName(req.getParameter("name")); //메서드에 req정의되어 있어 사용 가능 
		dto.setPass(req.getParameter("pass"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		
		//IP주소 추가
		
		dto.setIp(req.getRemoteAddr());
		System.out.println("M :"+dto);
		
		
		System.out.println(" N :"+ dto);
		
		//DB에 정보 저장
		//BoardDAO 생성 
		BoardDAO dao = new BoardDAO();
		
		//DB에 글 정보를 저장 
		dao.boradWrite(dto);
		
		//페이지 이동 정보 저장(리턴)
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo"); //어디로 갈건지 
		forward.setRedirect(true);//어떻게 갈건지 
		//true - sendRedirect()방식, false - forward()방식
		//주소랑 화면이 같이 바껴야할 것 같음 -> sendRedirect
		
		return forward;
	}
	
	
}
