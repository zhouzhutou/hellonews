<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/background.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"> 
    $(document).ready(function(){
    	$("#checkAll").click(function(){
    		if($("#checkAll").prop("checked")==true)
    		{
    			$("input[name='checkSingle']").each(function(index,element){
    				$(this).prop('checked',true);
    			});
    		}else{
    			console.log("wrong");
    			$("input[name='checkSingle']").each(function(index,element){
    				$(this).prop('checked',false);
    			});
    		}
    	})
    });
    function deleteComments(){
    	var check_vals=new Array();
    	$("input[name='checkSingle']:checked").each(function()
    	{
    		check_vals.push($(this).val());
    	});
    	if(check_vals.length==0)
    	{
    		alert("请选择要删除的数据");
    		return;
    	}
    	var commentIds=check_vals.join(',');
    	console.log(commentIds);
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	$.post("comment?action=delete",{commentId:commentIds},function(result){
    		var result=eval('('+result+')');
    		var winWidth=$(window).width();
    		var winHeight=$(window).height();
    		var dialogWidth=$("#messageDialog").width();
    		var dialogHeight=$("#messageDialog").height();
    		var top=(winHeight-dialogHeight)/2;
    		var left=(winWidth-dialogWidth)/2;
    		$("#messageDialog").css({"top":top+'px',"left":left+'px'}); 
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
    		var s_commentDate=$("#s_commentDate").val();
    		var e_commentDate=$("#e_commentDate").val();
    		var page=$(".pagination .active").first().text();
    		var t=setTimeout("window.location.href='comment?action=maintain&s_commentDate="
    			+s_commentDate+"&e_commentDate="+e_commentDate+"&page="+page+"'", 1000);
    	});
    }
    function deleteComment(id)
    {
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	$.post("comment?action=delete",{commentId:id},function(result){
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
    	   var s_commentDate=$("#s_commentDate").val();
           var e_commentDate=$("#e_commentDate").val();
           var page=$(".pagination .active").first().text();
    	   var t=setTimeout("window.location.href='comment?action=maintain&s_commentDate="
    			   +s_commentDate+"&e_commentDate="+e_commentDate+"&page="+page+"'",1000);
    	});
    }
 /*    $("#messageDialog")[0].addEventListener("webkitAnimationEnd",function(){
		msgDialg.removeClass("fade");
		msgDialg.hide();
		msgDialgDom.removeEventListener("webkitAnimationEnd");
	}); */
</script>
<style>
.search{
    padding-bottom:12px;
    height: 46px;
}
.search button{
    height:34px;
}
.content_list form{
    padding: 0px;
    margin: 0px;
    width: 465px;
    height: 34px;
}
.content_list form button{
    margin:0px;
}

</style>

<div class="content">
 <div class="header_nav">${navCode}</div>
 <div class="content_list">
 <div class="search">
<button type="button" class="btn btn-danger btn-sm" onclick="deleteComments()">批量删除</button>
 <form class="form-inline" method="post" action="${pageContext.request.contextPath }/comment?action=maintain" style="float:right;">
      发布日期&nbsp;&nbsp;
  <div class="form-group">
    <input type="text" class="form-control Wdate" style="height:34px;width:150px;" name="s_commentDate" id="s_commentDate" value="${datePicker.startDate }" onFocus="WdatePicker()"/>
  </div>
      至
  <div class="form-group">
    <input type="text" class="form-control Wdate" style="height:34px;width:150px;" name="e_commentDate" id="e_commentDate" value="${datePicker.endDate }" onFocus="WdatePicker()"/>
  </div>
  <button type="submit" class="btn btn-primary btn-sm">查询</button>
</form>
 </div>
     <table class="table table-hover table-bordered comment_table">
         <tr>
             <th><input type="checkbox" name="checkAll" id="checkAll"/></th>
             <th>编号</th>
             <th>评论内容</th>
             <th>评论主题</th>
             <th>评论者姓名</th>
             <th>评论时间</th>
             <th>操作</th>
         </tr>
     <c:forEach var="comment" items="${commentBackList }" varStatus="status">
         <tr>
            <td><input type="checkbox" name="checkSingle" id="checkSingle" value="${comment.commentId}"/></td>
            <td>${(status.index+1)}</td>
            <td><a title="${comment.comment }">${fn:substring(comment.comment,0,16)}...</a></td>
            <td><a title="${comment.title }" href="news?action=show&newsId=${comment.newsId }">${fn:substring(comment.title,0,16)}...</a></td>
            <td>${comment.userName }</td>
            <td><fmt:formatDate value="${comment.createAt }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <button type="button" class="btn btn-danger btn-sm" onclick="deleteComment(${comment.commentId})">删除</button>
            </td>
         </tr>
     </c:forEach>
     </table>
 <nav style="text-align:center">
  <ul class="pagination">
    ${commentBackPagination}
  </ul>
</nav>

 </div>
</div>

