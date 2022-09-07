package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("M: BoardContentAction_execute() 호출");
		
		//전달된 정보 저장(bno,pageNum)
		//왜 bno,pageNum 숫자 데이터인데 Integer로 형변환 해야하는가? 
		//=> 전달되는 파라미터 값의 경우, 테이블에 저장되는 값이면 형변환 O 
		//테이블에 저장이 안되는 값이면 형변환X
		int bno = Integer.parseInt(req.getParameter("bno"));
		String pageNum = req.getParameter("pageNum");
		
		//BoardDAO객체 생성
		BoardDAO dao = new BoardDAO();
		
		//조회수 1 증가하기 - updateReadCount
		dao.updateReadCount(bno);
		System.out.println("조회수 1 증가 완료");
		//게시판 글 1개의 정보를 가져와서 출력 
		BoardDTO dto = dao.getBoard(bno);
		
		//Model객체에서 정보 출력 X
		//view에서 정보 출력 O
		//=> model객체에 있는 정보를 view로 이동 
		
		//request영역에 저장
		req.setAttribute("dto", dto);
		//req.setAttribute("dto2", dao.getBoard(bno)); //같은 의미.. 
		
		//페이징 처리할 때 필요한 값 
		req.setAttribute("pageNum", pageNum); //정보를 출력할 때 사용
		
		//출력하는 view페이지로 이동 
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp"); //view(jsp)페이지로 이동 
		forward.setRedirect(false); 
		//영역에 데이터를 저장하는 순간 -> forward 방식으로 이동해야 함 
		
		System.out.println("게시판 글 1개 정보 가져오기 완료");
		
		return forward;
	}
}
