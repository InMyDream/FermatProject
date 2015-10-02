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
<title>수정하기</title>
</head>
<body>
<form role="form" action="modifyPage" method="post">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">

	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">번호</label>
			<input type="text" name="bno" class="form-control" value="${noticeBoardVO.bno }" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">제목</label>
			<input type="text" name="title" class="form-control" value="${noticeBoardVO.title}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">내용</label>
			<textarea class="form-control" name="content" rows="3">${noticeBoardVO.content }</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">작성자</label>
			<input type="text" name="writer" class="form-control" value="${noticeBoardVO.writer }">
		</div>
	</div>
</form>

<div class="box-footer">
	<button type="submit" class="btn btn-primary">저장</button>
	<button type="submit" class="btn btn-warning">취소</button>
</div>

<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click",function(){
			self.location = "/noticeboard/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
		});
		
		$(".btn-primary").on("click", function(){
			formObj.submit();
		});
	});
	
</script>

<%@ include file="../include/footer.jsp" %>
</body>
</html>