<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/foreground.css"/>
<title>Insert title here</title>
</head>
<body>
    <div class="news_show">
        <div class="news_show_header">
        <div class="news_show_header_nav">${newsShowNav}</div>
        <h3>${news.title }</h3>
        <div class="news_show_header_info"><span>发布时间：<fmt:formatDate value="${news.createAt }" pattern="yyyy.MM.dd"/></span><span>作者：${news.author }</span>
                       <span>新闻类别：${news.newsTypeName }</span><span>点击量：${news.click }</span>
        </div>
        </div>
        <div class="news_show_content">
          ${news.newsContent }
        </div>
        <div class="upAndDownNewsList">
         <c:choose>
             <c:when test="${upAndDownNewsList.get(0).newsId==-1 }">
             <p><span>上一页:&nbsp;&nbsp;</span>No news</p>
             </c:when>
             <c:otherwise>
             <p><span>上一页:&nbsp;&nbsp;</span><a href="news?action=show&newsId=${upAndDownNewsList.get(0).newsId}">${fn:substring(upAndDownNewsList.get(0).title,0,16)}</a></p>
             </c:otherwise>
         </c:choose>
         
          <c:choose>
             <c:when test="${upAndDownNewsList.get(1).newsId==-1 }">
             <p><span>下一页:&nbsp;&nbsp;</span>No news</p>
             </c:when>
             <c:otherwise>
             <p><span>下一页:&nbsp;&nbsp;</span><a href="news?action=show&newsId=${upAndDownNewsList.get(1).newsId}">${fn:substring(upAndDownNewsList.get(1).title,0,16)}</a></p>
             </c:otherwise>
         </c:choose>
        </div>
    </div>
    
    <div class="row">
    <div class="col-md-12 col-xs-12">
    <jsp:include page="/foreground/component/comment.jsp"></jsp:include>
    <form method="POST" action="comment?action=addComment">
       <input type="hidden" value="${ news.newsId}" name="newsId">
       <textarea name="comment" >
       </textarea>
        <button type="submit" class="btn btn-primary">发表</button>
    </form>
    </div>
   </div>
  
</body>
</html>