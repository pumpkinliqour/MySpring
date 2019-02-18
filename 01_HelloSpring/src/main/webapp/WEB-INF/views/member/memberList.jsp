<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원리스트" name="pageTitle"/>
</jsp:include>
<style>
	div#page-container{padding:15px;}
</style>
<form action="${path}/member/memberSearch.do" method="post">
	<div class="searchs" style="text-align:right;">
		<select name="searchType">
			<!-- <option value="" disabled selected>검색타입</option> -->
			<option value="userId" <c:if test="${'userId' eq param.searchType }"> selected</c:if>>아이디</option>
			<option value="userName" <c:if test="${'userName' eq param.searchType }"> selected</c:if>>이름</option>
			<option value="address" <c:if test="${'address' eq param.searchType }"> selected</c:if>>주소</option>
			<option value="email" <c:if test="${'email' eq param.searchType }"> selected</c:if>>이메일</option>
			<option value="phone" <c:if test="${'phone' eq param.searchType }"> selected</c:if>>전화번호</option>
		</select>
		<input type="search" name="keyword" value="${param.keyword!=null?param.keyword:''}"/>
		<input type="submit" class="btn btn-outline-primary" value="검색"/>
	</div>
</form>
<c:if test="${not empty list }">
	<table class="table">
			<tr>
				<th scope="col">번호</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">성별</th>
				<th scope="col">나이</th>
				<th scope="col">이메일</th>
				<th scope="col">연락처</th>
				<th scope="col">주소</th>
				<th scope="col">취미</th>
				<th scope="col">가입날짜</th>
				<th scope="col" width="1px"></th>
				<th scope="col"></th>
			</tr>
			<c:forEach items="${list}" var="member" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${member.USERID}</td>
					<td>${member.USERNAME}</td>
					<td>${member.GENDER=="M"?"남":"여"}</td>
					<td>${member.AGE}</td>
					<td>${member.EMAIL}</td>
					<td>${member.PHONE}</td>
					<td>${member.ADDRESS}</td>
					<td>
					<c:forEach items="${member.HOBBY}" var="hobby" varStatus="vs">
						${vs.index!=0?",":"" } ${hobby }
					</c:forEach>
					</td>
					<td>${member.ENROLLDATE}</td>
					<td>
						<button type="button" class="btn btn-default" 
						onclick="updatemember(${member.USERID});">
							수정
						</button>
					</td>
					<td>
						<button type="button" class="btn btn-default" 
						onclick="deletemember(${member.USERID});">
							삭제
						</button>
					</td>
				</tr>
			</c:forEach>
	</table>
</c:if>
<c:if test="${empty list }">
	<h3 style="text-align:center;">검색하신 결과가 없습니다.</h3>
</c:if>
		<div id="page-container">
			${pageBar }
		</div>
<script>
	function updatemember(userId)
	{
		location.href="${path}/demo/updateMember.do?userId="+uesrId;
	}
	function deletemember(userId)
	{
		location.href="${path}/demo/deleteMember.do?memberNo="+userId;
	}
</script>



<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>






