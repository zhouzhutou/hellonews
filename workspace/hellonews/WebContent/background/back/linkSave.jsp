<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function checkForm()
    {
    	var emailReg=new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
    	var numberReg=new RegExp(/^(0|[1-9][0-9]*)$/);
        var boolArr=new Array(4);
        for(var i=0;i<boolArr.length;i++)
        {
        	boolArr[i]=false;
        }
        if($.trim($("#linkName").val())=='')
        {
        	$("#linkName+p").html("*链接名称不能为空");
        	boolArr[0]=false;
        }else{
        	$("#linkName+p").html("");
        	boolArr[0]=true;
        }
        
        if($.trim($("#linkAddr").val())=='')
        {
        	$("#linkAddr+p").html("*链接地址不能为空");
        	boolArr[1]=false;
        }else{
        	$("#linkAddr+p").html("");
        	boolArr[1]=true;
        }
        
        if($.trim($("#linkEmail").val())=='')
        {
        	$("#linkEmail+p").html("*联系人不能为空");
        	boolArr[2]=false;
        }else if(!emailReg.test($("#linkEmail").val())){
        	$("#linkEmail+p").html("*邮箱格式错误");
        	boolArr[2]=false;
        }else{
        	$("#linkEmail+p").html("");
        	boolArr[2]=true;
        }
        
        if($.trim($("#orderNum").val())=='')
        {
        	$("#orderNum+p").html("*链接优先级不能为空");
        	boolArr[3]=false;
        }else if(!numberReg.test($("#orderNum").val())){
        	$("#orderNum+p").html("*数字格式不正确，请输入整数");
        	boolArr[3]=false;
        }else{
        	$("#orderNum+p").html("");
        	boolArr[3]=true;
        }
        
        for(var i=0;i<boolArr.length;i++)
        {
        	if(boolArr[i]==false)
        	{
        		return false;
        	}
        }
        return true;
    }
    $(document).ready(function(){
    	$("#save").bind('click',function(){
        	var bool=checkForm();
        	if(bool)
        	{
        		$("#messageDialog").hide();
            	$("#messageDialog").removeClass("fade");
        		$.post("link?action=save",{linkName:$("#linkName").val(),linkAddr:$("#linkAddr").val(),
        			linkEmail:$("#linkEmail").val(),orderNum:$("#orderNum").val(),
        			linkId:$("#linkId").val()},function(result){
        				//var result=eval('('+result+')');
        				var result=eval('('+result+')');
        	    	 	var winWidth=$(window).width();
        	    		var winHeight=$(window).height();
        	    		var dialogWidth=$("#messageDialog").width();
        	    		var dialogHeight=$("#messageDialog").height();
        	    		var top=(winHeight-dialogHeight)/2;
        	    		var left=(winWidth-dialogWidth)/2;
        	    		$("#messageDialog").css({"top":top+'px',"left":left+'px'}); 
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
</script>

<div class="content">
 <div class="header_nav">${navCode}</div>
 <div class="content_form">
<%--  <form class="form-horizontal" action="${pageContext.request.contextPath }/link?action=save" method="post" onsubmit="return checkForm()"> --%>
<form class="form-horizontal">
  <div class="form-group">
    <label for="linkName" class="col-sm-2 col-xs-2 control-label">链接名称</label>
    <div class="col-sm-8 col-xs-8 ">
      <input type="text" class="form-control" id="linkName" name="linkName" placeholder="链接名称" value=${link.linkName }>
       <p style="color:red;"></p>
    </div>
  </div>
  <div class="form-group">
    <label for="linkAddr" class="col-sm-2 col-xs-2  control-label">链接地址</label>
    <div class="col-sm-8 col-xs-8">
      <input type="text" class="form-control" id="linkAddr" name="linkAddr" placeholder="链接地址" value=${link.linkAddr }>
      <p style="color:red;"></p>
    </div>
  </div>
  <div class="form-group">
    <label for="linkEmail" class="col-sm-2 col-xs-2  control-label">联系人邮箱</label>
    <div class="col-sm-8 col-xs-8">
      <input type="text" class="form-control" id="linkEmail" name="linkEmail" placeholder="联系人邮箱" value=${link.email }>
       <p style="color:red;"></p>
    </div>
  </div>
  <div class="form-group">
    <label for="orderNum" class="col-sm-2 col-xs-2  control-label">链接优先级</label>
    <div class="col-sm-8 col-xs-8">
      <input type="text" class="form-control" id="orderNum" name="orderNum" placeholder="链接优先级" value=${link.orderNum }>
       <p style="color:red;"></p>
    </div>
  </div>
  
      <input type="hidden" class="form-control" id="linkId" name="linkId" value="${link.linkId }">
  <div class="buttons form-group">  
    <!--  <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="submit" value="保存"> -->
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="button" value="保存" id="save">
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-2 col-md-offset-2 btn btn-info" type="button" value="返回" onclick="javascript:window.history.back()">
  </div> 
   </form>
      </div>
</div>

