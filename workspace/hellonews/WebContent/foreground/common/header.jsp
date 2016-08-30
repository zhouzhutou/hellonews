<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="zhouzhutou.news.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/foreground.css">
<style type="text/css">
img{
    width:180px;
}
.menu_row{
 margin-bottom:18px;
}
.menu_col{
 padding:0;
}
#menu{
    list-style:none;
    width:100%;
    height:50px;
    padding:0;
    margin:0;
    border:1px lightgray solid;
    border-radius:10px;
    padding-top:8px !important;
}
#menu .mainPage{
   margin-left:8px;
}
#menu li{
    list-style-type:none;
    float:left;
    text-decoration:none;
}
#menu li a:hover{
    color:gray;
}
#menu li a{
   font: 22px/1.5 Tahoma,Helvetica,Arial,'宋体',sans-serif;
   color:#2e292e;
   text-decoration:none;
}

</style>
 <script type="text/javascript">
 $(document).ready(function(){
	 menuPos();
 })
 var menuPos=function(){
	 $("#menu li").each(function(index,element){
		 if($(this).index()!=0)
		 {
			 $(this).css({'margin-left':"8%"});
		 }
	 });
 }
 </script>
<div class="container">
<div class="row">
  <div class="col-md-12 col-xs-12"><img src="${pageContext.request.contextPath}/image/news.png" class="img-rounded img-responsive"></div>
</div>
 <div class="row menu_row">
  <div class="col-md-12 col-xs-12 menu_col">
      <ul id="menu">
          <li class="mainPage"><a href="goIndex">首页</a></li>
          <c:forEach var="newType" items="${newsTypeList}">
          <li><a href="news?action=list&typeId=${newType.newsTypeId }">${newType.newsTypeName }</a></li>
          </c:forEach>
      </ul>
 </div>
</div>
</div>



