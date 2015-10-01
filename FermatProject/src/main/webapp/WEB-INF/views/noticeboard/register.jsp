<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>등록하기</title>
</head>
<body>
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">제목</label>
				<input type="text" name='title' class="form-control" placeholder="제목을 입력하세요">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">내용</label>
				<textarea class="form-control" name="content" rows="3" placeholder="내용을 입력하세요"></textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label>
				<input type="text" name="writer" class="form-control" placeholder="이름을 입력하세요">
			</div>
		</div>
		<div class="box-footer">
				<button type="submit" class="btn btn-primary">등록</button>
		</div>
	</form>
	
<%@include file="../include/footer.jsp"%>
</body>
</html>