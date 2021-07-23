<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
	.container{border:1px solid #ddd;margin:auto;align:center;}
    #main{width:100%;}
    h2{width:100%;text-align:center;}
    table{width:90%;text-align:center;}
    #head{height:50px;background-color:rgba(100, 148, 237, 0.699);}
    #body{height:40px;}
    #footer{width:90%;margin:auto;}
    #navi{line-height:35px;}
    #backdiv{text-align:right;}
    #search{text-align:right;}
</style>
<script>
	$(function(){
		$("#addwrite").on("click",function(){
			location.href = "/bod/boardWrite";
		})
        $("#back").on("click",function(){
            location.href = "/home";
        })
        $("#backall").on("click",function(){
            location.href = "/bod/boardlist?cpage=1";
        })
	})
</script>
</head>
<body>
	<div class="container p-3">
        <div class="row pt-4" id="main">
            <h2><strong>자유게시판</strong></h2>
        </div>
		<div class="row p-2">
			<div class="col-5 pt-2">
		<c:choose>
			<c:when test="${key!=null}">
				${key} : ${word} 를 검색한 결과 입니다.
			</c:when>
		</c:choose>
			</div>
        	<div class="col-7 pt-2" id="search">
        	<form action="boardlist.board" method="get">
        		<select id="searchKey" name="key">
					<option value="title">제목</option>
					<option value="writer">작성자</option>
				</select>
				<input id="searchcontent" name="word" type="text">
				<input type="submit" value="검색 ">
				<input type="hidden" name="cpage" value="1">
			</form> 
        	</div>
        </div>
        <table border="1" align="center">
            <tr id="head">
                <th width=10%>No</th>
                <th align="center" width=50%>Title</th>
                <th align="center" width=15%>Writer</th>
                <th align="center" width=15%>Date</th>
                <th align="center" width=10%>View</th>
            </tr>
        <c:forEach var="list" items="${list}">
            <tr id="body">
                <td>${list.seq}</td>
                <td><a href="/bod/viewProc?seq=${list.seq}">${list.title}</a></td>
                <td>${list.writer}</td>
                <td>${list.write_date}</td>
                <td>${list.view_count}</td>
            </tr>
        </c:forEach>
        </table>
        <div id="footer" class="row pt-2">
        	<div class="col-2 p-0" id="write">
            	<input type="button" value="글 쓰기" id="addwrite" class="btn btn-primary">
            </div>
            <div class="col-8" style="text-align:center" id="navi">
	<c:choose>
		<c:when test="${key==null}">
			<c:forEach var="navi" items="${navis}" varStatus="s"> <!-- varStatus : var의 상태, 정보를 기억, 알수 있다. -->
				<c:choose>
					<c:when test="${navi == '>'}">
						<a href="${pageContext.request.contextPath}/boardlist.board?cpage=${navis[s.index-1]+1}">${navi}</a>
					</c:when>
					<c:when test="${navi == '<'}">
						<a href="${pageContext.request.contextPath}/boardlist.board?cpage=${navis[s.index+1]-1}">${navi}</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/boardlist.board?cpage=${navi}">${navi}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="navi" items="${navis}" varStatus="s"> <!-- varStatus : var의 상태, 정보를 기억, 알수 있다. -->
				<c:choose>
					<c:when test="${navi == '>'}">
						<a href="${pageContext.request.contextPath}/boardlist.board
								?cpage=${navis[s.index-1]+1}&key=${key}&word=${word}">${navi}</a>
					</c:when>
					<c:when test="${navi == '<'}">
						<a href="${pageContext.request.contextPath}/boardlist.board
								?cpage=${navis[s.index+1]-1}&key=${key}&word=${word}">${navi}</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/boardlist.board
								?cpage=${navi}&key=${key}&word=${word}">${navi}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:otherwise>
	</c:choose>
		</div>	
     		<div class="col-2 p-0" id="backdiv">
     	<c:choose>
			<c:when test="${key==null}">
				<input type="button" value="뒤로가기" id="back" class="btn btn-secondary">
			</c:when>
			<c:otherwise>
				<input type="button" value="전체목록으로 돌아가기" id="backall" class="btn btn-secondary">
			</c:otherwise>		
		</c:choose>
     		</div>       
        </div>
    </div>
</body>
</html>