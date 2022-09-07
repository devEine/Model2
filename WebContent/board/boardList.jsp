<%@page import="com.itwillbs.board.db.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL사용  -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>boardListAll.jsp</h1>
	<!-- EL/JSTL 사용  -->
	
	<h2>게시판 목록</h2>

   
   <h3><a href="./BoardWrite.bo">글 쓰기(new)</a></h3>
   
   
   <table border="1">
      <tr>
        <td>번호</td>
        <td>제목</td>
        <td>글쓴이</td>
        <td>조회수</td>
        <td>작성일</td>
        <td>IP</td>
      </tr>
      
      <c:forEach var="dto" items="${boardList}">
      
	      <tr>
	       <!--제목 누를 시 해당 주소로 이동 -->
	        <td>${dto.bno}</td>
	        <td>
	       <a href="./BoardContent.bo?bno=${dto.bno}&pageNum=${requestScope.pageNum}">${dto.subject }</a>
	       <!-- 전체 글 번호와 페이지번호 주소창에 표시 -->
	       </td>
	        <td>${dto.name }</td>
	        <td>${dto.readcount }</td>
	        <td>$[dto.date}]</td>
	        <td>${dto.ip }</td>
	      </tr>
      </c:forEach>
      
   
   </table>
		
		<c:if test="${cnt!=0}">
		
			<c:if test="${startPage > pageBlock }">
				   <a href="./BoardList.bo?pageNum=${startPage-pageBlock}">[이전]</a>			
			</c:if>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
					  <a href="./BoardList.bo?pageNum=${ i}">[${i }]</a> 
			</c:forEach>
			
			<c:if test="${endPage <pageCount }">
					   <a href="./BoardList.bo?pageNum=${startPage+pageBlock}">[다음]</a>
			</c:if>
		
		</c:if>

</body>
</html>