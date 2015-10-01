<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
    
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록 전체보기</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th style="width: 40px">No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th style="width: 80px">조회 수</th>
		</tr>
		
		<c:forEach items="${list }" var="noticeBoardVO">
			<tr>
				<td>${noticeBoardVO.bno }</td>
				<td><a href='/noticeboard/read?bno=${noticeBoardVO.bno }'>${noticeBoardVO.title }</a></td>
				<td>${noticeBoardVO.writer }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${noticeBoardVO.regdate }" /></td>
				<td><span class="badge bg-red">${noticeBoardVO.viewcnt }</span></td>
			</tr>
		</c:forEach>
	</table>

<script>
	var result='${msg}';
	
	if(result == 'success'){
		alert('처리가 완료되었습니다');
	}
	
</script>

<%@include file="../include/footer.jsp" %>
</body>
</html>