<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="comment">
<h4>最新评论</h4>
<hr>
<%--  <c:if test="${commentsList.isEmpty() }"> --%>
 <c:forEach var="comment" items="${commentsList }">
       <div class="title">
        <span class="userName">${comment.userName}</span>&nbsp;&nbsp;<span class="date">[<fmt:formatDate value="${comment.createAt }" type="time" pattern="yyyy-MM-dd HH:mm:ss"/>]</span>
       </div>
       
        <div class="content">
         ${comment.comment}
       </div>
<hr>
</c:forEach>
<%-- </c:if> --%>
</div>