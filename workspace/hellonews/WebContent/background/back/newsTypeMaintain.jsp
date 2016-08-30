<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/background.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"> 
    function deleteNewsType(id)
    {
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	$.post("newsType?action=delete",{newsTypeId:id},function(result){
    		var result=eval('('+result+')');
    		layScreenCenter($(window),$("#messageDialog"));
    	    var msgDialg=$("#messageDialog");
    		//var msgDialgDom=msgDialg[0];
    		//layScreenCenter($(window),$("#messageDialog"));
    		if(result.success.bool==true)
    		{
    		    $("#messageDialog #message").html(result.success.message);
    		    $("#messageDialog img").first().attr("src","image/check.png");
    		    $('#messageDialog').show();
    		    $('#messageDialog').addClass("fade");
    		}else{
    			$("#messageDialog #message").html(result.success.message);
    			$("#messageDialog img").first().attr("src","image/error.png");
    			$('#messageDialog').show();
    			$('#messageDialog').addClass("fade");
    		}
    	   var t=setTimeout("window.location.href='newsType?action=maintain'",1000);
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
 /*    $("#messageDialog")[0].addEventListener("webkitAnimationEnd",function(){
		msgDialg.removeClass("fade");
		msgDialg.hide();
		msgDialgDom.removeEventListener("webkitAnimationEnd");
	}); */

</script>
<div class="content">
 <div class="header_nav">${navCode}</div>
 <div class="content_list">
     <table class="table table-hover table-bordered newsType_table">
         <tr>
             <th>编号</th>
             <th>链接名称</th>
             <th>操作</th>
         </tr>
     <c:forEach var="newsType" items="${newsTypeBackList }" varStatus="status">
         <tr>
            <td>${(status.index+1)}</td>
            <td>${newsType.newsTypeName }</td>
            <td>
                <button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location.href='newsType?action=preSave&newsTypeId=${newsType.newsTypeId}'">修改</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-danger btn-sm" onclick="deleteNewsType(${newsType.newsTypeId})">删除</button>
            </td>
         </tr>
     </c:forEach>
     </table>
 </div>
</div>

