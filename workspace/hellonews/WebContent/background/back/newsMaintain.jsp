<%@page import="zhouzhutou.news.util.PropertiesUtil"%>
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
    function deleteNewses(){
    	var check_vals=new Array();
    	$("input[name='checkSingle']:checked").each(function(index,element){
    		check_vals.push($(this).val())
    	});
    	if(check_vals.length==0)
    	{
    	    alert("请选择要删除的数据");	
    	    return;
    	}
    	var newsIds=check_vals.join(',');
    	console.log(newsIds);
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	layScreenCenter($(window),$("#messageDialog"));
    	$.post("news?action=delete",{newsIds:newsIds},function(result){	
    		result=eval('('+result+')');
    		if(result.bool)
    		{
    			$("#messageDialog #message").html(result.message);
     		    $("#messageDialog img").first().attr("src","image/check.png");
     		    $('#messageDialog').show();
     		    $('#messageDialog').addClass("fade");
    		}else{
    			$("#messageDialog #message").html(result.message);
     		    $("#messageDialog img").first().attr("src","image/error.png");
     		    $('#messageDialog').show();
     		    $('#messageDialog').addClass("fade");
    		}
    		var s_newsCreateAt=$("#s_newsCreateAt").val();
    		var e_newsCreateAt=$("#e_newsCreateAt").val();
    		var page=$(".pagination .active").first().text();
    		var newsTitle_search=$("#newsTitle_search").val();
    		var t=setTimeout("window.location.href='news?action=maintain&s_newsCreateAt="
    			+s_newsCreateAt+"&e_newsCreateAt="+e_newsCreateAt+"&page="+page+"&newsTitle_search"+newsTitle_search+"'",
    			1000);
    	});
    }
    function deleteNews(id)
    {
    	$("#messageDialog").hide();
    	$("#messageDialog").removeClass("fade");
    	layScreenCenter($(window),$("#messageDialog"));
    	$.post("news?action=delete",{newsIds:id},function(result){
    		var result=eval('('+result+')');
    		//var msgDialgDom=msgDialg[0];
    		if(result.bool)
    		{
    		    $("#messageDialog #message").html(result.message);
    		    $("#messageDialog img").first().attr("src","image/check.png");
    		    $('#messageDialog').show();
    		    $('#messageDialog').addClass("fade");
    		}else{
    			$("#messageDialog #message").html(result.message);
    			$("#messageDialog img").first().attr("src","image/error.png");
    			$('#messageDialog').show();
    			$('#messageDialog').addClass("fade");
    		}
   		   var s_newsCreateAt=$("#s_newsCreateAt").val();
   		   var e_newsCreateAt=$("#e_newsCreateAt").val();
   		   var page=$(".pagination .active").first().text();
   		   var newsTitle_search=$("#newsTitle_search").val();
    	   var t=setTimeout("window.location.href='news?action=maintain&s_newsCreateAt="+s_newsCreateAt
    			   +"&e_newsCreateAt="+e_newsCreateAt+"&page="+page+"&newsTitle_search="+newsTitle_search+"'",
    			   1000);
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
    width: 700px;
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
<button type="button" class="btn btn-danger btn-sm" onclick="deleteNewses()">批量删除</button>
 <form class="form-inline" method="post" action="${pageContext.request.contextPath }/news?action=maintain" style="float:right;">
       新闻标题&nbsp;&nbsp;
  <div class="form-group">
     <input type="text" class="form-control Wdate" style="height:34px;width:150px;" name="newsTitle_search" id="newsTitle_search" value="${newsSearchTitle}"}>
  </div>
  &nbsp;&nbsp;   
      发布日期&nbsp;&nbsp;
  <div class="form-group">
    <input type="text" class="form-control Wdate" style="height:34px;width:150px;" name="s_newsCreateAt" id="s_newsCreateAt" value="${datePicker.startDate }" onFocus="WdatePicker()"/>
  </div>
      至
  <div class="form-group">
    <input type="text" class="form-control Wdate" style="height:34px;width:150px;" name="e_newsCreateAt" id="e_newsCreateAt" value="${datePicker.endDate }" onFocus="WdatePicker()"/>
  </div>
  <button type="submit" class="btn btn-primary btn-sm">查询</button>
</form>
 </div>
     <table class="table table-hover table-bordered comment_table">
         <tr>
             <th><input type="checkbox" name="checkAll" id="checkAll"/></th>
             <th>编号</th>
             <th>新闻标题</th>
             <th>新闻类别</th>
             <th>发布时间</th>
             <th>操作</th>
         </tr>
     <c:forEach var="news" items="${newsBackList }" varStatus="status">
         <tr>
            <td><input type="checkbox" name="checkSingle" id="checkSingle" value="${news.newsId}"/></td>
            <% int pageSize=Integer.parseInt(PropertiesUtil.getProperty("pageBackSize")); %>
            <td>${(page-1)*4+status.index+1}</td>
            <td><a title="${news.title }" href="news?action=show&newsId=${news.newsId }">
                <c:choose>
                    <c:when test="${fn:length(news.title)>16 }">
                         ${fn:substring(news.title,0,16)}...
                    </c:when>
                    <c:otherwise>
                         ${news.title}
                    </c:otherwise>
                </c:choose>
            </a></td>
            <td>${news.newsTypeName }</td>
            <td><fmt:formatDate value="${news.createAt }" pattern="yyyy-MM-dd"/></td>
            <td>
             <button type="button" class="btn btn-info btn-sm" onclick="javascript:window.location.href='news?action=preSave&newsId=${news.newsId}'">修改</button>
             <button type="button" class="btn btn-danger btn-sm" onclick="deleteNews(${news.newsId})">删除</button>
            </td>
         </tr>
     </c:forEach>
     </table>
 <nav style="text-align:center">
  <ul class="pagination">
    ${newsBackPagination}
  </ul>
</nav>

 </div>
</div>


