<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function checkForm()
    {
        var bool=false;
        if($.trim($("#newsTypeName").val())=='')
        {
        	$("#newsTypeName+p").html("*链接名称不能为空");
        	bool=false;
        }else{
        	$("#newsTypeName+p").html("");
        	bool=true;
        }
        if(bool==false)
        {
        	return false;
        }else{
        	return true;
        }
    }
    $(document).ready(function(){
    	$("#save").bind('click',function(){
        	var bool=checkForm();
        	if(bool)
        	{
        		$("#messageDialog").hide();
            	$("#messageDialog").removeClass("fade");
        		$.post("newsType?action=save",{newsTypeName:$("#newsTypeName").val(),
        			newsTypeId:$("#newsTypeId").val()},function(result){
        				//var result=eval('('+result+')');
        				var result=eval('('+result+')');
        	    	 	/*var winWidth=$(window).width();
        	    		var winHeight=$(window).height();
        	    		var dialogWidth=$("#messageDialog").width();
        	    		var dialogHeight=$("#messageDialog").height();
        	    		var top=(winHeight-dialogHeight)/2;
        	    		var left=(winWidth-dialogWidth)/2;
        	    		$("#messageDialog").css({"top":top+'px',"left":left+'px'}); */
        	    		layScreenCenter($(window),$("#messageDialog"));
        	    	    var msgDialg=$("#messageDialog");
        				if(result.success.bool)
        				{
        					$("#messageDialog #message").html(result.success.message);
        					$("#messageDialog img").first().attr("src","image/systemImg/check.png");
        					$("#messageDialog").show();
        					$("#messageDialog").addClass("fade");
        				}else{
        					$("#messageDialog #message").html(result.success.message);
        					$("#messageDialog img").first().attr("src","image/systemImg/error.png");
        					$("#messageDialog").show();
        					$("#messageDialog").addClass("fade");
        				}
        			});
        	}
        });
    });
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

<div class="content">
 <div class="header_nav">${navCode}</div>
 <div class="content_form">
<%--  <form class="form-horizontal" action="${pageContext.request.contextPath }/link?action=save" method="post" onsubmit="return checkForm()"> --%>
<form class="form-horizontal">
  <div class="form-group">
    <label for="newsTypeName" class="col-sm-2 col-xs-2 control-label">新闻类别名称</label>
    <div class="col-sm-8 col-xs-8 ">
      <input type="text" class="form-control" id="newsTypeName" name="newsTypeName" placeholder="链接名称" value="${newsType.newsTypeName }">
       <p style="color:red;"></p>
    </div>
  </div>
   <input type="hidden" class="form-control" id="newsTypeId" name="newsTypeId" value="${newsType.newsTypeId }">
  <div class="buttons form-group">  
    <!--  <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="submit" value="保存"> -->
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="button" value="保存" id="save">
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-2 col-md-offset-2 btn btn-info" type="button" value="返回" onclick="javascript:window.history.back()">
  </div> 
   </form>
      </div>
</div>

