<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
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
	<td><a href="/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${noticeBoardVO.bno}">${noticeBoardVO.title }<strong>[${noticeBoardVO.replycnt }]</strong></a></td>
	<td>${noticeBoardVO.writer }</td>
	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${noticeBoardVO.regdate }" /></td>
	<td><span class="badge bg-red">${noticeBoardVO.viewcnt }</span></td>
	</tr>
</c:forEach>
	</table>

<div class="text-center">
	<ul class="pagination">
		<c:if test="${pageMaker.prev }">
			<li><a href="list${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="list${pageMaker.makeSearch(idx) }">${idx }</a>
			</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="list${pageMaker.makeSearch(pageMaker.endPage+1) }">&raquo;</a></li>
		</c:if>
	</ul>
</div>

<div class="box-body">
	<select name="searchType">
		<option value="n" <c:out value="${cri.searchType == null? 'selected':'' }" />>--------------</option>
		<option value="t" <c:out value="${cri.searchType eq 't'? 'selected':'' }" />>제목</option>
		<option value="c" <c:out value="${cri.searchType eq 'c'? 'selected':'' }" />>내용</option>
		<option value="w" <c:out value="${cri.searchType eq 'w'? 'selected':'' }" />>작성자</option>
		<option value="tc" <c:out value="${cri.searchType eq 'tc'? 'selected':'' }" />>제목/내용</option>
		<option value="cw" <c:out value="${cri.searchType eq 'cw'? 'selected':'' }" />>내용/작성자</option>
		<option value="tcw" <c:out value="${cri.searchType eq 'tcw'? 'selected':'' }" />>제목/내용/작성자</option>
	</select>
	
	
	<input type="text" name="keyword" id="keywordInput" value="${cri.keyword }">
	<button id="searchBtn">검색</button>
	<button id="newBtn">글쓰기</button>

</div>

<form id="jobForm">
	<input type="hidden" name="page" value=${pageMaker.cri.page }>
	<input type="hidden" name="perPageNum" value=${pageMaker.cri.perPageNum }>
</form>

<script>
	$(document).ready(function(){
		$('#searchBtn').on("click", function(event){
			self.location = "list"+'${pageMaker.makeQuery(1)}'+"&searchType="+$("select option:selected").val()+"&keyword="+$('#keywordInput').val();
		});
		
		$('#newBtn').on("click", function(evt){
			self.location = "register";
		})
	});
	
</script>

<%@ include file="../include/footer.jsp" %>
</body>
</html>