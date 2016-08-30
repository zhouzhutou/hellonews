<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/background.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"> 
    function deleteLink(id)
    {
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	$.post("link?action=delete",{linkId:id},function(result){
    		var result=eval('('+result+')');
    	 	var winWidth=$(window).width();
    		var winHeight=$(window).height();
    		var dialogWidth=$("#messageDialog").width();
    		var dialogHeight=$("#messageDialog").height();
    		var top=(winHeight-dialogHeight)/2;
    		var left=(winWidth-dialogWidth)/2;
    		$("#messageDialog").css({"top":top+'px',"left":left+'px'}); 
    	    var msgDialg=$("#messageDialog");
    		//var msgDialgDom=msgDialg[0];
    		//layScreenCenter($(window),$("#messageDialog"));
    		if(result.success.bool)
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
    	   var t=setTimeout("window.location.href='link?action=maintain'",1000);
    	});
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
     <table class="table table-hover table-bordered link_table">
         <tr>
             <th>编号</th>
             <th>链接名称</th>
             <th>链接地址</th>
             <th>联系人邮箱</th>
             <th>链接优先级</th>
             <th>操作</th>
         </tr>
     <c:forEach var="link" items="${linkBackList }" varStatus="status">
         <tr>
            <td>${(status.index+1)}</td>
            <td>${link.linkName }</td>
            <td>${link.linkAddr }</td>
            <td>${link.email }</td>
            <td>${link.orderNum }</td>
            <td>
                <button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location.href='link?action=preSave&linkId=${link.linkId}'">修改</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-danger btn-sm" onclick="deleteLink(${link.linkId})">删除</button>
            </td>
         </tr>
     </c:forEach>
     </table>
 </div>
</div>

