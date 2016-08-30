<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zhouzhutou.news.model.Admin"%>
<style type="text/css">
    .headSegOne img{
        width:180px;
        height:100px;
    }
    .headSegOne,.headSegTwo,.headSegThree{
        height:100px;
        font: 14px/1 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;
        color:#05051d;
    }
    .headSegTwo span{
        color:lightseagreen;;
        margin-left:4px;
        margin-right:4px;
    }
     .headSegTwo a{
       	color:#05051d;
        cursor: pointer;
     }
    .headSegTwo,.headSegThree{
    padding-top:84px;
    }
</style>
<script>
    var logOut=function(){
    	$(".headSegTwo a").first().bind("click",function(){
    		var bool=confirm("确认退出zhouzhutou新闻管理系统吗？");
    		if(bool==true)
    		{
    			 window.location.href="adminPassport?action=logout";
    		}else
    		{
    			return;
    		}
    	});
    }
    $(document).ready(function(){
    	logOut();
    });
</script>
<%
   Admin currentUser=(Admin)request.getSession().getAttribute("currentUser");
%>
<div class="container">
<div class="row">
    <div class="col-md-4 col-xs-4 headSegOne">
     <img src="${pageContext.request.contextPath }/image/news.png"/>
    </div>
     <div class="col-md-4 col-xs-4 headSegTwo">
                   欢迎管理员:<span><%=currentUser.getName() %></span> <a>[安全退出]</a>
    </div>
     <div class="col-md-4 col-xs-4 headSegThree">
      <span></span>
    </div>
</div>
</div>