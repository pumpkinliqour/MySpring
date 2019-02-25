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
/*중복아이디체크관련*/
div#member-container{position:relative; padding:0px;}
div#member-container span.guide {display:none;font-size: 12px;position:absolute; top:12px; right:50px;}
div#member-container span.ok{color:green;}
div#member-container span.error{color:red;}
</style>
<script>
	$(function(){
		$("#password_2").blur(function(){
		
			var p1=$("#password_1").val(), p2=$("#password_2").val();
			if(p1!=p2){
				alert("패스워드가 일치하지 않습니다.");
				$("#password_2").val('');
				$("#password_1").focus();
			}
		});
	});
	$(function(){
		$("#userId").keyup(function(){
			var userId=$("#userId").val().trim();
			if(userId.length<4)
			{
				$(".guide").hide();
				return;
			}
			$.ajax({
				url:"${path}/member/memberCheckId.do",
				data:{"userId":userId},
				success:function(data){
					console.log(data);
					if(data.isId==true)
					{
						$(".guide.ok").hide();
						$(".guide.error").show();            					
					}
					else{
						$(".guide.ok").show();
						$(".guide.error").hide();
					}	
				}
			});
		});
	});
</script>
	<div id="member-container">
		<form id="memberFrm">
			<div class="form-group row">
				<label for="userId" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">아이디</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userId" name="userId"/>
					<span class="guide ok">이 아이디는 사용 할 수 있습니다.</span>
					<span class="guide error">이 아이디는 사용 할 수 없습니다.</span>
					<input type="hidden" name="checkId" value="0"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="password_1" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password_1" name="password"/>
				</div>
				<label for="password_2" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">비밀번호 확인</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password_2" name="password"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="userName" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userName" name="userName"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" 	id="gender0" value="M"/>
						<label class="form-check-label" for="gender0" style="font-size:15px; font-weight:bold;">남</label>
						<span>&nbsp;&nbsp;</span>
						<input class="form-check-input" type="radio" name="gender" 	id="gender1" value="F"/>
						<label class="form-check-label" for="gender1" style="font-size:15px; font-weight:bold;">여</label>
					</div>
				</div>
			</div>	
			<div class="form-group row">
				<label for="age" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">나이</label>
				<div class="col-sm-10">
					<input id="age" name="age" type="number" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="email" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">이메일</label>
				<div class="col-sm-10">
					<input id="email" name="email" type="email" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="phone" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">연락처</label>
				<div class="col-sm-10">
					<input id="phone" name="phone" type="tel" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="address" class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">주소</label>
				<div class="col-sm-10">
					<input id="address" name="address" type="text" class="form-control"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" style="font-size:15px; font-weight:bold;">취미</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby0" value="운동"/>
						<label class="form-check-label" for="hobby0" style="font-size:15px; font-weight:bold;">운동</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby1" value="등산"/>
						<label class="form-check-label" for="hobby1" style="font-size:15px; font-weight:bold;">등산</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby2" value="독서"/>
						<label class="form-check-label" for="hobby2" style="font-size:15px; font-weight:bold;">독서</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby3" value="영화"/>
						<label class="form-check-label" for="hobby3" style="font-size:15px; font-weight:bold;">영화</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby4" value="게임"/>
						<label class="form-check-label" for="hobby4" style="font-size:15px; font-weight:bold;">게임</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby5" value="쇼핑"/>
						<label class="form-check-label" for="hobby5" style="font-size:15px; font-weight:bold;">쇼핑</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby6" value="음악"/>
						<label class="form-check-label" for="hobby6" style="font-size:15px; font-weight:bold;">음악</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby7" value="낚시"/>
						<label class="form-check-label" for="hobby7" style="font-size:15px; font-weight:bold;">낚시</label>
						<span>　</span>
						<input class="form-check-input" type="checkbox" name="hobby" id="hobby8" value="여행"/>
						<label class="form-check-label" for="hobby8" style="font-size:15px; font-weight:bold;">여행</label>
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