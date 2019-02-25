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
$(function(){
   $('[name=upFile]').on('change',function(){
      //var filename=$(this).val();
      var filename=this.files[0].name;
      //var filename=$(this).prop('files')[0].name;
      $(this).next('.custom-file-label').html(filename);
   });
 });
function validate(){
	var content=$('[name=content]').val();
	var title=$('[name=boardTitle]').val();
	if(content.trim().length==0)
	{
		alert("내용을 입력하세요!");
		return false;
	}
	if(title.trim().length<4)
	{
		alert("4글자 이상 입력하세요!");
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
        <form name="boardFrm" action="${pageContext.request.contextPath}/board/boardFormEnd.do" method="post" onsubmit="return validate();"  enctype="multipart/form-data">
            <input type="text" style="margin-left:484px; background-color:rgb(154, 185, 243); color:white; width:180px;" class="form-control" name="boardWriter" id="boardWriter" value="${sessionScope.userId}" readonly required/>
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
            <textarea name="boardContent" id="boardContent" class="form-control" rows="10" style="resize:none;" required></textarea>
            </div>
           <div class="input-group mb-3" style="padding:0px;">
                <div class="input-group-prepend" style="padding:0px;">
                    <label for="upFile1"><span class="input-group-text">첨부파일①</span></label>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="upFile" id="upFile1">
                    <label class="custom-file-label" for="upFile1"></label>
                </div>
                <div class="input-group-prepend" style="padding:0px;">
                    <label for="upFile2"><span class="input-group-text">첨부파일②</span></label>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="upFile" id="upFile2">
                    <label class="custom-file-label" for="upFile2"></label>
                </div>
            </div>
            <input type="hidden" name="boardReadCount" value="${board.boardReadCount }"/>
            <input type="submit" class="btn btn-outline-success" value="작성완료"/>
        </form>
    </div>
    
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>