package com.itwillbs.board.action;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8088/Model2/board2.bo
public class BoardFrontController extends HttpServlet{
	//프로젝트 우클릭 - 설정 - 자바필드패스 - 에드라이브러리 - 톰캣 추가
	

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET,POST방식 모두 호출 - doProcess()실행 ");
		
		System.out.println("\n 1.가상주소 계산 시작");
		//1.가상주소 계산--------------------------------------------------
			//req.getRequestURL();
			//URL: http://localhost:8088/Model2/board2.bo
			String requestURI = req.getRequestURI();
			//URI:/Model2/board2.bo
			System.out.println("C:requestURI :"+requestURI); //URI주소 확인
								//C: Controller의 의미 
			
			String ctxPath = req.getContextPath();
			System.out.println("C: ctxPath :"+ctxPath);
			
			//URI의 /Model2 부분 삭세
			String command = requestURI.substring(ctxPath.length());
			//substring : 데이터 자르기
			//ctxPath의 길이만큼 데이터를 잘라서 가져오겠다는 뜻 
			System.out.println("C: command :"+command);
		//1.가상주소 계산--------------------------------------------------
			System.out.println("\n 1.가상주소 계산 끝");
			
			System.out.println("\n 2.가상주소 매핑 시작");
		//2.가상주소 매핑--------------------------------------------------
			Action action = null;
			ActionForward forward = null; //여러번 사용할 예정이라 변수 미리 선언해놓음
		//ActionForward 자바 페이지 생성하여 사용 (페이지 이동을 위한 정보를 저장하는 객체)
		if(command.equals("/BoardWrite.bo")){ //만약 주소가 맞아서 실행된다면 
			System.out.println("C : /BoardWrite.bo 호출 ");
			System.out.println("C: DB정보가 필요없음 -view페이지로 이동");
			
			//글쓰기 페이지 보여주기(DB정보 필요없음 -> Model2방식에서 view로 보내야함) 
			//(DB정보 필요하면 -> M ionForward(); //객체 생성
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp"); //어디로 갈것인지 
			forward.setRedirect(false); //어떤 방식으로 갈 것인지 설정 
			//false니까 forward방식으로 이동 
			
			
		}else if(command.equals("/BoardWriteAction.bo")){
			System.out.println("C:/BoardWriteAction.bo호출");
			System.out.println("C:DB정보가 필요없음-view페이지로 이동");
			
			BoardWriteAction bwAction = new BoardWriteAction();
			try {
				action = new BoardWriteAction();//인터페이스를 통해 자동형변환  -> 응집도를 떨어트리기 위해 이렇게 구현 (객체지향)
				
				
				//forward = bwAction.execute(req, resp);
				forward = action.execute(req, resp); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                   
		}else if(command.equals("/BoardList.bo")){
			System.out.println("C : /BoardList.bo 호출");
			System.out.println("C : DB정보가 필요, 페이지 이동X, 페이지 출력O");
			action = new BoardListAction();
			//BoardListAction 객체 생성
			BoardListAction listAction = new BoardListAction();
			try {
				//forward = listAction.execute(req, resp); //예외처리해야 오류 안뜸 
				forward = action.execute(req, resp);  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardContent.bo")){
			//게시판의 글을 누르면 글내용으로 이동하는 주소로 이동 			
			System.out.println(" C: /BoardContent.bo호출");
			System.out.println("C: DB정보 사용, 출력 ");
			
			//BoardContentAction 객체 생성
			action = new BoardContentAction();
			//action에서 상속받은 execute재정의 메서드 이용~
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//2.가상주소 매핑--------------------------------------------------
		System.out.println("\n 2.가상주소 매핑 끝");
		
		System.out.println("\n 3.가상주소 이동 시작");
		//3.가상주소 이동--------------------------------------------------
			if(forward != null){
				if(forward.isRedirect()){
					//true -> sendRedirect 방식으로 움직임
					
					System.out.println("C : true"+forward.getPath()+"이동, sendRedirect방식 ");
					
					resp.sendRedirect(forward.getPath());
				}else{
					//false -> forward 방식으로 움직임 
					System.out.println("C : true"+forward.getPath()+"이동, forward방식 ");
					RequestDispatcher dis
					= req.getRequestDispatcher(forward.getPath());
					dis.forward(req, resp);
				}
			}
		
		//3.가상주소 이동--------------------------------------------------
		System.out.println("\n 3.가상주소 이동 끝");
		
	
	}//////////////////////////////doProcess///////////////////////////////
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET방식 호출 - doGET()실행 ");
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST방식 호출 - doPOST()실행 ");
		doProcess(req, resp);
	}

}
