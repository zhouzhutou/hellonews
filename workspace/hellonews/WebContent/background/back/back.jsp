<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="zhouzhutou.news.model.Admin"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/background.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/background.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<title>Insert title here</title>
<script>
    function refreshService()
    {
    	$("#messageDialog").hide();
	    $("#messageDialog").removeClass("fade");
    	layScreenCenter($(window),$("#messageDialog"));
    	$.post("init",function(result){
    		    console.log(result);
				var result=eval('('+result+')'); 
				if(result.bool)
				{
					$("#messageDialog #message").html(result.message);
					$("#messageDialog img").first().attr("src","image/systemImg/check.png");
					$("#messageDialog").show();
					$("#messageDialog").addClass("fade");
				}else{
					$("#messageDialog #message").html(result.message);
					$("#messageDialog img").first().attr("src","image/systemImg/error.png");
					$("#messageDialog").show();
					$("#messageDialog").addClass("fade");
				}
			});
    }
    var layScreenCenter=function(parent,child)
    {
    	var parentWidth=parent.width();
    	var parentHeight=parent.height();
    	var childWidth=child.width();
    	var childHeight=child.height();
    	var top=(parentHeight-childHeight)/2;
    	var left=(parentWidth-childWidth)/2;
    	child.css({"top":top+'px',"left":left+'px'});
    }
</script>
</head>
<body>
<%   
/* String mainPage=(String)request.getAttribute("mainPage");
if(mainPage==null||mainPage.equals(""))
{
	   mainPage=new String("/background/back/default.jsp");
} */ %>
     <div class="container" id="first_container">
        <jsp:include page="/background/common/header.jsp"></jsp:include>
        <div class="row center">
            <div class="col-md-3 col-xs-3">
                <ul class="list-group left-bar">
                    <li class="list-group-item manager"><a href="goIndex">首页</a></li>
                    <li class="list-group-item manager">新闻管理</li>
                    <li class="list-group-item"><a href="news?action=preSave">&nbsp;&nbsp;新闻添加</a></li>
                    <li class="list-group-item"><a href="news?action=maintain">&nbsp;&nbsp;新闻维护</a></li>
                    <li class="list-group-item manager">新闻评论管理</li>
                    <li class="list-group-item"><a href="comment?action=maintain">&nbsp;&nbsp;新闻评论维护</a></li>
                    <li class="list-group-item manager">新闻类别管理</li>
                    <li class="list-group-item"><a href="newsType?action=preSave">&nbsp;&nbsp;新闻类别添加</a></li>
                    <li class="list-group-item"><a href="newsType?action=maintain">&nbsp;&nbsp;新闻类别维护</a></li>
                    <li class="list-group-item manager">友情链接管理</li>
                    <li class="list-group-item"><a href="link?action=preSave">&nbsp;&nbsp;友情链接添加</a></li>
                    <li class="list-group-item"><a href="link?action=maintain">&nbsp;&nbsp;友情链接维护</a></li>
                    <li class="list-group-item manager">系统管理</li>
                    <li class="list-group-item" style="cursor:pointer" onclick="refreshService()"><a>&nbsp;&nbsp;刷新服务器缓存</a></li>
                </ul>
            </div>
            <div class="col-md-9 col-xs-9">
                <jsp:include page="${mainPage }"></jsp:include> 
            </div>
        <jsp:include page="/background/common/footer.jsp"></jsp:include>  
     </div>
     </div>
      <jsp:include page="/background/common/messageDialog.jsp"></jsp:include>
</body>
</html>