<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>
	<% 
		//MVC실행 프로젝트 실행 시작페이지
		//* MVC프로젝트에서는 index.jsp페이지 외 절대로 .jsp페이지 실행을 금지함 *
		//-> 주소창에 .jsp 주소가 보이면 MVC패턴 깨짐 
		
		//response.sendRedirect("./Test.bo");
	
		//글쓰기 페이지 이동
		//response.sendRedirect("./BoardWrite.bo");
		
	
		//글 리스트 페이지 이동 
		response.sendRedirect("./BoardList.bo");
		
		//가상 주소 사용시
		//상대주소
		//java(서블릿: 컨트롤러): ./Test.bo
		// .(프로젝트명)
		
		
		//실제 주소 사용시
		//jsp(view): ./Test.jsp
		// .(WebContent)
	%>
</body>
</html>