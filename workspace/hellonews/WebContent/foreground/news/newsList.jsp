<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/forefround.css"/>
<style type="text/css">
.table-condensed tr .collumn_one{
	width:30%;
}
.table-condensed tr .collumn_two{
	width:70%;
}
</style>

 <div class="type_news_list"> 
     <div class="data_header">${newsListNav }</div>
     <div class="data_list">
       <table class="table table-condensed">
           <tr>
               <th class="collumn_one">Time</th>
               <th class="collumn_two">Title</th>
           </tr>
           <c:forEach var="news" items="${typeNewsList }">
           <tr>
               <td class="collumn_one"><fmt:formatDate value="${news.createAt }" pattern="yyyy-MM-dd"/></td>
               <td class="collumn_two"><a href="news?action=show&newsId=${news.newsId }" title="${news.title }">${fn:substring(news.title,0,18)}</a></td>
           </tr>
           </c:forEach>
       </table>
            ${newsListPagination }
       </div>
</div>

