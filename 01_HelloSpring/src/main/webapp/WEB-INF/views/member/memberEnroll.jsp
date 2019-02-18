<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원가입" name="pageTitle"/>
</jsp:include>

<style>
div#member-container{
	width:40%;
	padding:15px;
	margin:0 auto;
	border:1px solid lightgray;
	border-radius: 10px;
}
</style>
	<div id="member-container">
		<form id="memberFrm">
			<div class="form-group row">
				<label for="userId" 
				class="col-sm-2 col-form-label">
				아이디</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					id="userId" name="userId"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="password" 
				class="col-sm-2 col-form-label">
				비밀번호</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" 
					id="password" name="password"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="userName" 
				class="col-sm-2 col-form-label">
				이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" 
					id="userName" name="userName"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" 
						id="gender0" value="M"/>
						<label class="form-check-label" for="gender0">남</label>
						<span>　</span>
						<input class="form-check-input" type="radio" name="gender" 
						id="gender1" value="F"/>
						<label class="form-check-label" for="gender1">여</label>
					</div>
				</div>
			</div>	
			<div class="form-group row">
				<label for="age" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<input id="age" name="age" type="number" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input id="email" name="email" type="email" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="phone" class="col-sm-2 col-form-label">연락처</label>
				<div class="col-sm-10">
					<input id="phone" name="phone" type="tel" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="address" class="col-sm-2 col-form-label">주소</label>
				<div class="col-sm-10">
					<input id="address" name="address" type="text" 
					class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">취미</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby0" value="운동"/>
						<label class="form-check-label" for="hobby0">운동</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby1" value="등산"/>
						<label class="form-check-label" for="hobby1">등산</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby2" value="독서"/>
						<label class="form-check-label" for="hobby2">독서</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby3" value="영화"/>
						<label class="form-check-label" for="hobby3">영화</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby4" value="게임"/>
						<label class="form-check-label" for="hobby4">게임</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby5" value="쇼핑"/>
						<label class="form-check-label" for="hobby5">쇼핑</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby6" value="음악"/>
						<label class="form-check-label" for="hobby6">음악</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby7" value="낚시"/>
						<label class="form-check-label" for="hobby7">낚시</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" 
						id="hobby8" value="여행"/>
						<label class="form-check-label" for="hobby8">여행</label>
					</div>
				</div>
			</div>
			<div class="list-group">
				<button type="button" onclick="member4();" 
				class="list-group-item list-group-item-action" style="text-align:center; color:white; background-color:cornflowerblue;">
				회원가입</button>
				<button type="button" onclick="member5();" 
				class="list-group-item list-group-item-action" style="text-align:center; color:white; background-color:cornflowerblue;">
				취소</button>
			</div>	
		</form>	
	</div>
	<script>
		function member4()
		{
			$('#memberFrm').attr("action", "${pageContext.request.contextPath}/member/memberEnroll.do");
			$('#memberFrm').attr("method", "post");
			$('#memberFrm').submit();
		}
		function member5()
		{
			location.href="${path}";
		}
	</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>