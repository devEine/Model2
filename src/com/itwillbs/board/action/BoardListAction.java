package com.itwillbs.board.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("M(model) : BoardListAction_execute() 호출");
		
		//BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		//===================페이징 처리 ============================
		
		//게시판에 작성되어 있는 전체 글 갯수를 알아야함 
		int cnt = dao.getBoardCount();
		
		//한페이지에 보여줄 글의 갯수 설정 
		//int pageSize = 10;
		String urlPageSize = req.getParameter("pageSize");
		if(urlPageSize==null){ //한 번에 몇개 보여줄지 정하지 않았을때 = pageSize가 없을때?
			urlPageSize="2"; //자동으로 10개 보이게 설정 
		}
		int pageSize = Integer.parseInt(urlPageSize); //페이지 사이즈가 있으면 그대로 urlPageSize에 저장
		
		//현재 페이지가 몇번째 페이지인지 계산
		//-> 페이지 정보가 없을 경우 항상 1페이지 
		String pageNum = req.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		
		//페이지 시작행 번호 계산  1 11 21 31 ....... pageSize가 10일시
		int currentPage = Integer.parseInt(pageNum);  //받아온 현재 페이지를 int로 바꿔줌
		
		int startRow = (currentPage-1)*pageSize+1;
		
		//끝 행 번호 계산  10, 20, 30, 40, 50 ........
		int endRow = currentPage * pageSize;
		
		
		//===================페이징 처리 ============================
		
		
		//===============글정보 가져오기 시작 ===========================
		//dao 메서드 중 게시판 글정보를 모두 가져오는 메서드 호출 
		//List<BoardDTO> boardList = dao.getBoardList();
		List<BoardDTO> boardList = dao.getBoardList(startRow,pageSize);
		
		System.out.println(" M : 게시판 글정보  저장완료 !");
		System.out.println("boardList:"+boardList);
		
		//==================페이징 처리 2(하단 페이지 링크)==============================
		
		//전체 페이지 수 계산 
		//ex)  전체 글 50개 -> 한페이지 10개씩 출력, 5개 페이지  필요 
		//ex)  전체 글 55개 -> 한페이지 10개씩 출력, 6개 페이지  필요 
		
		int pageCount = cnt/pageSize + (cnt%pageSize==0? 0:1) ; // 전체 글 갯수 /한페이지에 보이는 글 갯수
										//나누어 떨어지면 페이지 0 추가, 나누어 떨어지지 않으면 페이지 1추가 
		
		//한 화면에 보여줄 페이지수 (페이지 블럭) -> 쇼핑몰 맨 밑에 페이지 이동하는 블럭
		int pageBlock = 3;
		
		//페이지 블럭의 시작 번호 -> 1,2......,9,10 이면 -> 시작번호:1  
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
						//int : 정수형 -> 0.2는 0으로 간주 -> 1~10까지는 모두 startPage =1로 산출됨 
		
		//페이지 블럭의 끝 번호  1~10 => 10, 11~20 => 20 
		int endPage = startPage + pageBlock -1; 
		//총 페이지 수, 페이지 블럭의 끝 번호 비교 
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		
		//==================페이징 처리 2==============================
		
		//view페이지 정보 전달을 위해서 request영역에 저장 
		req.setAttribute("boardList",boardList);
		System.out.println("M : 리스트 정보 저장 request영역 ");
		
		//페이징 처리 정보 전달 (request영역)
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("cnt", cnt);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageBlock", pageBlock);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		System.out.println("M: 페이징 처리정보 저장 ");
		
		//화면에 출력
		//페이지 이동 (화면 전환)
		ActionForward forward = new ActionForward();
		//forward.setPath("./board/boardListAll.jsp");
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);
		//여기서는 false:forward 방식으로 가야함 -> .jsp가 주소창에 노출되면 안됨 
		//방식 2가지 ->  true: sendRedirect 주소가 바뀌면서 화면 전환(지정한 주소로 바뀜)
		// false : forward 주소는 안바뀌고 화면만 바뀜 
		
		return forward;
		//=================글정보 가져 오기 끝 ==========================
	}

}
