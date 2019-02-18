<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="pageTitle"/>
</jsp:include>
<script>
	function validate(){
		var content=$('[name=content]').val();
		if(content.trim().length==0)
		{
			alert("내용을 입력하세요!");
			return false;
		}
		return true;
	}
</script>
<style>
    div#update-container{width:700px; margin:0 auto; text-align:center;}
    div#update-container input, div#update-container select {margin-bottom:10px;}
</style>
    <div id="update-container">
    <div class="navbar-header">
		<a class="navbar-brand" href="${path }/board/boardList.do">게시판</a>
	</div>
        <form action="${path}/board/boardWriteEnd.do" method="post" enctype="multipart/form-data">
        
            <input type="text" style="margin-left:484px; background-color:rgb(154, 185, 243); color:white; width:180px;" class="form-control" name="boardWriter" id="boardWriter" value="작성자 / ${sessionScope.userId}" readonly required/>
        
        	<div class="col-sm-12">
        	<div class="row">
        	<label for="boardTitle" class="col-sm-2 control-label" style="float:left;">제목</label>
        	</div>
            <input type="text" id="boardTitle" class="form-control" placeholder="4글자 이상 써주십시오." name="boardTitle" required>
            </div>
            
            <div class="col-sm-12">
            <div class="row"> 
            <label for="boardContent" class="col-sm-2 control-label" style="float:left;">내용</label>
            </div>
            <textarea name="boardContent" id="boardContent" class="form-control" rows="10" style="resize:none;"></textarea>
            </div>
            
            <div class="col-sm-12">
        	<div class="row">
        	<label for="boardOriginalFileName" class="col-sm-2 control-label" style="float:left;">첨부파일</label>
        	</div>
            <input type="file" style="float:left;" id="boardOriginalFileName" name="boardOriginalFileName">
            </div>
            
            <input type="hidden" name="boardReadCount" value="${board.boardReadCount }"/>
            
            <br/>
            <br/>
            <input type="submit" class="btn btn-outline-success" onclick="return validate();" value="작성완료"/>
        </form>
    </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>