<%@page import="java.util.List"%>
<%@page import="zhouzhutou.news.model.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
//    List<News> imageNewsList=(List)request.getAttribute("imageNewsList"); 
//    int initialImageId=imageNewsList.get(0).getNewsId();
//    String initialImageName=imageNewsList.get(0).getImageName();   
%>
<%-- <%=initialImageId %> --%>
<%-- <%=initialImageName %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-wideth,initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/foreground.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
   
</script>
<title>Insert title here</title>

</head>
<body>
<div class="container">
<jsp:include page="/foreground/common/header.jsp"/>

    <div class="row-fluid">
        <div class="col-md-8 col-xs-8">
          <jsp:include page="${newsVary}"></jsp:include>
        </div>
        <div class="col-md-4 col-xs-4">
      
             <div class="newsList_news_list">
                 <h6 class="newsList_news_list_NewsListHeader">最热新闻</h6>
                    <div class="data_list">
                         <ul>
                         <c:forEach var="clickMostNews" items="${clickMostNewsList }">
                         <li><a href="news?action=show&newsId=${clickMostNews.newsId}" title="${clickMostNews.title }">${fn:substring(clickMostNews.title,0,16)}</a></li>
                          </c:forEach>
                         </ul>
                     </div>
             </div>
             
             <div class="newsList_news_list">
                 <h5 class="newsList_news_list_NewsListHeader">最近新闻</h5>
                    <div class="data_list">
                         <ul>
                         <c:forEach var="newestNews" items="${newestNewsList}">
                         <li><a href="news?action=show&newsId=${newestNews.newsId}" title="${newestNews.title }">${fn:substring(newestNews.title,0,16)}</a></li>
                          </c:forEach>
                         </ul>
                     </div>
             </div>
        
      </div>
    </div>

</div>
</body>
</html>