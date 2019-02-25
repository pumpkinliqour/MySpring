<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="pageTitle"/>
</jsp:include>

<div id="board-container">
    <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" value="${board.BOARDTITLE }" required>
    <input type="text" class="form-control" name="boardWriter" value="${sessionScope.userId}" readonly required>

    <c:forEach items="${attach}" var="a" varStatus="vs">
        <button type="button" class="btn btn-outline-success btn-block" onclick="fileDownload('${a.ORIGINALFILENAME}','${a.RENAMEDFILENAME}');">
            첨부파일${vs.count} - ${a.ORIGINALFILENAME}
        </button>
    </c:forEach>
    
    <textarea class="form-control" name="boardContent" placeholder="내용" required style="resize: none; height:15em;">${board.BOARDCONTENT }</textarea>
</div>

<script>
	function fileDownload(oName, rName)
	{
		oName=encodeURIComponent(oName); //한글일 가능성이 있음
		location.href="${path}/board/fileDownLoad.do?oName="+oName+"&rName="+rName;
	}
</script>


 <style>
div#board-container{width:400px; margin:0 auto; text-align:center;}
div#board-container input,div#board-container button{margin-bottom:15px;}
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}
</style>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>