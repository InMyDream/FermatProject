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
<title>공지사항 읽기</title>
</head>
<body>

<form role="form" method="post">
	<input type="hidden" name="bno" value="${noticeBoardVO.bno }">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">제목</label>
		<input type="text" name="title" class="form-control" value="${noticeBoardVO.title }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">내용</label>
		<textarea class="form-control" name="content" rows="3" readonly="readonly">${noticeBoardVO.content }</textarea>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">작성자</label>
		<input type="text" name="writer" class="form-control" value="${noticeBoardVO.writer }" readonly="readonly">
	</div>
</div>

<div class="box-footer">
	<button type="submit" class="btn btn-warning">수정</button>
	<button type="submit" class="btn btn-danger">삭제</button>
	<button type="submit" class="btn btn-primary">목록보기</button>
</div>

<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "/noticeboard/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function(){
			formObj.attr("method", "post");
			formObj.attr("action", "/noticeboard/removePage");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function(){
			formObj.attr("method","get");
			formObj.attr("action", "/noticeboard/listPage");
			formObj.submit();
		});
	});
</script>

<%@ include file="../include/footer.jsp" %>
</body>
</html>