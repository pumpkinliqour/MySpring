<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="공지사항" name="pageTitle"/>
</jsp:include>

<style>
    #container {
      width: 70%;
      margin: 0 auto;     /* 가로로 중앙에 배치 */
      padding-top: 10%;   /* 테두리와 내용 사이의 패딩 여백 */
    }
    
	div#page-container{padding:15px;}
     
    #write {
      text-align: right;
    }
     
    /* Bootstrap 수정 */
    .table > thead {
      background-color: #b3c6ff;
    }
    .table > thead > tr > th {
      text-align: center;
    }
    .table-hover > tbody > tr:hover {
      background-color: #e6ecff;
    }
    .table > tbody > tr > td {
      text-align: center;
    }
    .table > tbody > tr > #title {
      text-align: left;
    }
     
    div > #paging {
      text-align: center;
    }
     
    .hit {
      animation-name: blink;
      animation-duration: 1.5s;
      animation-timing-function: ease;
      animation-iteration-count: infinite;
      /* 위 속성들을 한 줄로 표기하기 */
      /* -webkit-animation: blink 1.5s ease infinite; */
    }
     
    /* 애니메이션 지점 설정하기 */
    /* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
      /* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
    }
  </style>
</head>
<body>
  <div id="container">
   	
   	<c:if test="${sessionScope.userId=='admin'}">
    <div id="write">
      <a href="#">글쓰기</a>
    </div>
    </c:if>
     
    <div>
      <table class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th width="10%">번호</th>
            <th width="48%">제목</th>
            <th width="10%">작성자</th>
            <th width="20%">작성일</th>
            <th width="12%">파일이미지</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="notice" items="${list}" varStatus="status">
            <tr>
              <td>${notice.NOTICENO}</td>
              <td id="title">
                <c:if test="${notice.depth > 0}">
                  &nbsp;&nbsp;
                </c:if>
                <a href="#">${notice.NOTICETITLE}</a>
              </td>
              <td>${notice.NOTICEWRITER}</td>
              <td>${notice.NOTICEDATE}</td>
              <td>
              	<c:if test="${not empty notice.FILEPATH }">
              		<img alt='첨부파일' src="${path }/images/file.png" width="16px">
              	</c:if>
              </td>
            <tr>
          </c:forEach>
        </tbody>
      </table>
       
      <!-- Paging 처리 -->
      <div id="page-container">
			${pageBar }
		</div>
    </div>
  </div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>