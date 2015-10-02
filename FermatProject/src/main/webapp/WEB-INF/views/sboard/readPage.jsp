<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 읽기</title>
</head>
<body>

<form role="form" method="post">
	<input type="hidden" name="bno" value="${noticeBoardVO.bno }">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
	<input type="hidden" name="searchType" value="${cri.searchType }">
	<input type="hidden" name="keyword" value="${cri.keyword }">
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

<ul class="timeline">
	<li class="time-label" id="repliesDiv"><span class="bg-green">댓글 목록<small id='replycntSmall'>[${noticeBoardVO.replycnt }]</small>
	</span></li>
</ul>

<div class="text-center">
	<ul id="pagination" class="pagination pagination-sm no-margin">
	
	</ul>
</div>

<div class="row">
	<div class="col-md-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">댓글 추가하기</h3>
			</div>
			<div class="box-body">
				<label for="exampleInputEmail1">작성자</label>
				<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
				<label for="exampleInputEmail1">댓글 작성</label>
				<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
			</div>
			<div class="box-footer">
				<button type="submit" class="btn btn-primary" id="replyAddBtn">확인</button>
			</div>
		</div>
	</div>
</div>

<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body" data-rno>
				<p><input type="text" id="replytext" class="form-control"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="replyModBtn">수정</button>
				<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "/sboard/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function(){
			formObj.attr("method", "post");
			formObj.attr("action", "/sboard/removePage");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function(){
			formObj.attr("method","get");
			formObj.attr("action", "/sboard/list");
			formObj.submit();
		});
	});
</script>

<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
<i class="fa fa-comments bg-blue"></i>
	<div class="timeline-item">
		<span class="time">
			<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
		</span>
		<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
		<div class="timeline-body">{{replytext}}</div>
			<div class="timeline-footer">
				<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">수정</a>
			</div>
		</div>
	</div>
</li>
{{/each}}
</script>

<script>
Handlebars.registerHelper("prettifyDate", function(timeValue){
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth()+1;
	var date = dateObj.getDate();
	return year+"/"+month+"/"+date;
});

var printData = function(replyArr, target, templateObject){
	var template = Handlebars.compile(templateObject.html());
	
	var html = template(replyArr);
	$(".replyLi").remove();
	target.after(html);
}

var bno = ${noticeBoardVO.bno};
var replyPage = 1;

function getPage(pageInfo){
	$.getJSON(pageInfo, function(data){
		printData(data.list, $("#repliesDiv"), $('#template'));
		printPaging(data.pageMaker, $(".pagination"));
		
		$("#modifyModal").modal('hide');
		$("#replycntSmall").html("["+data.pageMaker.totalCount+"]");
	});
}

var printPaging = function(pageMaker, target){
	var str = "";
	
	if(pageMaker.prev){
		str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
	}
	
	for(var i=pageMaker.startPage, len = pageMaker.endPage; i<=len;i++){
		var strClass = pageMaker.cri.page == i? 'class=active':'';
		str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
	}
	
	target.html(str);
}

$("#repliesDiv").on("click", function(){
	if($(".timeline li").size() > 1){
		return;
	}
	getPage("/replies/"+bno+"/1");
});

$(".pagination").on("click", "li a", function(event){
	event.preventDefault();
	replyPage = $(this).attr("href");
	getPage("/replies/"+bno+"/"+replyPage);
});

$("#replyAddBtn").on("click", function(){
	var replyerObj = $("#newReplyWriter");
	var replytextObj = $("#newReplyText");
	var replyer = replyerObj.val();
	var replytext = replytextObj.val();
	
	$.ajax({
		type: 'post',
		url: '/replies/',
		headers:{
			"Content-Type": "application/json",
			"X-HTTP-Method-Override": "POST"
		},
		dataType: 'text',
		data: JSON.stringify({bno:bno, replyer:replyer, replytext:replytext}),
		success:function(result){
			console.log(result);
			if(result == 'success'){
				alert("등록되었습니다");
				replyPage=1;
				getPage("/replies/"+bno+"/"+replyPage);
				replyerObj.val("");
				replytextObj.val("");
			}
		}
	});
});

$(".timeline").on("click", ".replyLi", function(event){
	var reply = $(this);
	
	$("#replytext").val(reply.find('.timeline-body').text());
	$(".modal-title").html(reply.attr("data-rno"));
});

$("#replyModBtn").on("click",function(){
	var rno = $(".modal-title").html();
	var replytext = $("#replytext").val();
	
	$.ajax({
		type: 'put',
		url: '/replies/'+rno,
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"PUT"
		},
		data:JSON.stringify({replytext:replytext}),
		dataType:'text',
		success:function(result){
			console.log("result: "+result);
			if(result == 'success'){
				alert("수정 되었습니다");
				getPage("/replies/"+bno+"/"+replyPage);
			}
		}
	});
});

$("#replyDelBtn").on("click",function(){
	var rno = $(".modal-title").html();
	var replytext = $("#replytext").val();
	
	$.ajax({
		type:'delete',
		url:'/replies/'+rno,
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"DELETE"
		},
		dataType:'text',
		success:function(result){
			console.log("result: "+result);
			if(result == 'success'){
				alert('삭제 되었습니다');
				getPage("/replies/"+bno+"/"+replyPage);
			}
		}
	});
});
</script>

<%@ include file="../include/footer.jsp" %>
</body>
</html>