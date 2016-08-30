<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
    $(document).ready(function(){
    	disImageField();
	    $("#save").click(function(){
	    	$("#messageDialog").hide();
			$("#messageDialog").removeClass("fade");
	    	var check=checkForm();
	    	if(check==true)
	    	{
	    		//$("#newsSaveForm")[0].submit();
	    		var formData=new FormData($("#newsSaveForm")[0]);
	    		/*$.ajax("news?action=save",data:formData,function(result){
	    			var result=eval('('+result+')');
	    			layScreenCenter($(window),$("#messageDialog"));
	    			if(result.success.bool)
	    			{
	    				$("#messageDialog #message").html(result.success.message);
    					$("#messageDialog img").first().attr("src","image/systemImg/check.png");
    					$("#messageDialog").show();
    					$("#messageDialog").addClass("fade");
	    			}else{
	    				$("#messageDialog #message").html(result.success.message);
	    				$("#message img").first().attr("src","image/systemImg/error.png");
	    				$("#messageDialog").show();
	    				$("#messageDialog").addClass("fade");
	    			}
	    		});*/
	    		formData.append('newsContent',CKEDITOR.instances.newsContent.getData());
	    		$.ajax(
	    			{
	    				url:'news?action=save',
	    			    type:'POST',
	    			    cache:false,
	    			    data:formData,
	    			    processData:false,
	    			    contentType:false,
	    			    success:function(result){
	    			    	var result=eval('('+result+')');
	    			    	layScreenCenter($(window),$("#messageDialog"));
	    	    			if(result.success.bool)
	    	    			{
	    	    				$("#messageDialog #message").html(result.success.message);
	        					$("#messageDialog img").first().attr("src","image/systemImg/check.png");
	        					$("#messageDialog").show();
	        					$("#messageDialog").addClass("fade");
	    	    			}else{
	    	    				$("#messageDialog #message").html(result.success.message);
	    	    				$("#message img").first().attr("src","image/systemImg/error.png");
	    	    				$("#messageDialog").show();
	    	    				$("#messageDialog").addClass("fade");
	    	    			}
	    			    },
	    			error:function(XMLHttpRequest,textStatus,errorThrown){
	    				    alert(XMLHttpRequest.status+':'+XMLHttpRequest.statusText);
	    			    }
	    			}
	    		);
	    	}
	    });
    });
    
    //隐藏文件域
    function disImageField()
    {
    	if($("#isImage").prop('checked')==true)
    	{
    		$("#imageFileFiled").show();
    	}else{
    		$("#imageFileFiled").hide();
    	}
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
    //验证表单
    var checkForm=function()
    {
    	var checkArr=new Array(5);
    	for(var i=0;i<checkArr.length;i++)
    	{
    		checkArr[i]=false;
    	}
    	var newsTitle=$("#newsTitle").val();
    	var author=$("#author").val();
    	var newsType=$("#newsTypeList").find("option:selected").val();
    	var newsContent=CKEDITOR.instances.newsContent.getData();
    	var checked=$("#isImage").prop("checked");
    	console.log(checked);
    	if($.trim(newsTitle)==''||newsTitle==null)
    	{
    		$("#newsTitle+p").text("*新闻标题不能为空");
    		checkArr[0]=false;
    	}else{
    		$("#newsTitle+p").empty();
    		checkArr[0]=true;
    	}
    	if($.trim(author)==''||author==null)
    	{
    		$("#author+p").text("*新闻作者不能为空");
    		checkArr[1]=false;
    	}else{
    		$("#author+p").empty();
    		checkArr[1]=true;
    	}
    	if($.trim(newsType)==''||newsType==null)
    	{
    		$("#newsTypeList+p").text("*请选择新闻类别");
    		checkArr[2]=false;
    	}else{
    		$("#newsTypeList+p").empty();
    		checkArr[2]=true;
    	}
    	if(newsContent.length==0)
    	{
    		$("#textareaMsg").text("*新闻内容不能为空");
    		checkArr[3]=false;
    	}else{
    		$("#textareaMsg").empty();
    		checkArr[3]=true;
    	}
    	if(checked==true)
    	{
    		if($("#imgFile").val()=="")
    		{
    			$("#imgFile+p").text("*请选择要上传的图片");
        		checkArr[4]=false;
    		}else{
    			$("#imgFile+p").empty();
    			console.log($("#imgFile").val());
        		checkArr[4]=true;
    		}
    	}else{
    		$("#imgFile+p").empty();
    		checkArr[4]=true;
    	}
    	
    	for(var i=0;i<checkArr.length;i++)
    	{
    		if(checkArr[i]==false)
    			return false;
    	}
    	return true;
    }
</script>
<div class="content">
 <div class="header_nav">${navCode}</div>
 <div class="content_form">
<%--  <form class="form-horizontal" action="${pageContext.request.contextPath }/link?action=save" method="post" onsubmit="return checkForm()"> --%>
<form id="newsSaveForm" class="form-horizontal" method="post" action="news?action=save" enctype="multipart/form-data">
  <div class="form-group">
    <label for="newsTitle" class="col-sm-2 col-xs-2 control-label">新闻标题</label>
    <div class="col-sm-8 col-xs-8 ">
      <input type="text" class="form-control" id="newsTitle" name="newsTitle" placeholder="新闻标题" value="${backNews.title }">
       <p style="color:red;"></p>
    </div>
  </div>
  
  <div class="form-group">
    <label for="author" class="col-sm-2 col-xs-2 control-label">新闻作者</label>
    <div class="col-sm-4 col-xs-4 ">
      <input type="text" class="form-control" id="author" name="author" placeholder="新闻作者" value="${backNews.author }">
       <p style="color:red;"></p>
    </div>
  </div>
  
   <div class="form-group">
    <label for="newsTypeList" class="col-sm-2 col-xs-2 control-label">新闻类别</label>
    <div class="col-sm-4 col-xs-4 ">
       <select name="newsTypeList" id="newsTypeList" class="form-control">
           <option value="">请选择新闻类别</option>
           <c:forEach var="newsType" items="${newsTypeList }">
               <option value="${newsType.newsTypeId }" ${newsType.newsTypeId==backNews.newsTypeId?"selected":"" }>${newsType.newsTypeName }</option>
           </c:forEach>
       </select>
       <p style="color:red;"></p>
    </div>
  </div>
  
   <div class="form-group" style="height:46px">
    <label class="col-sm-2 col-xs-2 control-label">新闻属性</label>
    <div class="col-sm-4 col-xs-4 ">
      <label class="checkbox-inline">
          <input type="checkbox" id="isHead" name="isHead" value="1" ${backNews.isHead==1 ?"checked":""}> 头条
      </label>
      <label class="checkbox-inline">
          <input type="checkbox" id="isImage" name="isImage" value="1" ${backNews.isImage==1?"checked":"" } onclick="disImageField()"> 幻灯
      </label>
      <label class="checkbox-inline">
          <input type="checkbox" id="isHot" name="isHot" value="1" ${backNews.isHot==1?"checked":"" }> 热点
      </label>
    </div>
  </div>
  
   <div class="form-group" id="imageFileFiled" style="display:none">
    <label for="imgFile" class="col-sm-2 col-xs-2 control-label">上传图片</label>
    <div class="col-sm-4 col-xs-4 ">
      <input type="file" id="imgFile" name="imgFile" style="padding-top:5px;padding-bottom:5px;">
      <input type="hidden" id="preImage" name="preImage" value="${backNews.imageName }">
       <p style="color:red;"></p>
    </div>
  </div>
  
   <div class="form-group">
    <label for="newsContent" class="col-sm-2 col-xs-2 control-label" style="pading-top:0px">新闻内容</label>
    <div class="col-sm-10 col-xs-10 ">
       <textarea name="newsContent" id="newsContent">${backNews.newsContent}</textarea>
       <p style="color:red;" id="textareaMsg"></p>
       <script>
           CKEDITOR.replace('newsContent');
       </script>
    </div>
  </div>
  <input type="hidden" class="form-control" id="newsId" name="newsId" value="${backNews.newsId }">
  <div class="buttons form-group">  
    <!--  <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="submit" value="保存"> -->
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-3 col-xs-offset-3 btn btn-info" type="button" value="保存" id="save">
     <input class="btn btn-default col-md-2 col-xs-2 col-md-offset-2 col-md-offset-2 btn btn-info" type="button" value="返回" onclick="javascript:window.history.back()">
  </div> 
   </form>
      </div>
</div>
<script>
/* if('$(backNews.isImage)'==1)
{
	$("#imageFileFiled").show();
}else{
	$("#imageFileFiled").hide();
} */
</script>