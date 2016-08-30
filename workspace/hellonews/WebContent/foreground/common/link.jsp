<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/foreground.css">
<div class="container">
<div class="row">
  <div class="col-md-12 col-xs-12">
      <div class="panel panel-success">
           <div class="panel-heading">
            <h3 class="panel-title">友情链接</h3>
           </div>
          <div class="panel-body text-muted">
              <ul id="link">
<!--                    <li><a href="#">电子科技大学</a></li> -->
<!--                    <li><a href="#">清华大学</a></li> -->
<!--                    <li><a href="#">家里蹲大学</a></li> -->
           <c:forEach var="link" items="${linkList}">
               <li><a href="${link.linkAddr}">${link.linkName }</a></li>
           </c:forEach>
              </ul>
          </div>
      </div>
  </div>
</div>
</div>