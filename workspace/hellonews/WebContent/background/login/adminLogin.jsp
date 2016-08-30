<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/background.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
    body{
        backgroundcolor:#d7d7d7;
    }
</style>

<script type="text/javascript">
 $(window).ready(function(){
	 var form=$("form")[0];
	 var error=$(".errorMessage").first();
	 var nameEmpty=$("<p>*请输入管理员账号</p>");
	 var passwordEmpty=$("<p>*请输入管理员密码</p>");
	 var nameBool=false;
	 var passwordBool=false;
	 /* error.find("p").each(function(index,element){
		 $(this).empty();
	 }); */
	 $("#loginSub").bind("click",function(){
		 var inputName=$("#inputName");
		 var inputPassword=$("#inputPassword");
		    error.find("p").remove();
		 	if($.trim(error.find("p:contains('输入错误')").first().html())!='')
			{
				error.find("p:contains('输入错误')").first().remove();
			} 
			if($.trim(inputName.val())=='')
			{
				 error.append(nameEmpty); 
				 nameBool=false;
			}else{
				 error.find("p:contains('账号')").remove();
				 nameBool=true;
			}
			if($.trim(inputPassword.val())=='')
			{
				error.append(passwordEmpty);
				nameBool=false;
			}else{
				error.find("p:contains('密码')").remove();
				passwordBool=true;
			}
			if(nameBool&&passwordBool)
			{
				$("form")[0].submit();
			}
		});
}); 
</script>
<title>后台登陆</title>
</head>
<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4 col-xs-4 col-xs-offset-4 col_login">
<%-- ${pageContext.request.contextPath } --%>
      <form class="form-signin login" method="post" action="${pageContext.request.contextPath}/adminPassport?action=login">
        <h2 class="form-signin-heading">新闻发布系统后台管理</h2>
        <label for="inputName" class="sr-only">Email address</label>
        <input type="text" id="inputName" class="form-control" placeholder="管理员账号" required="" autofocus="" name="adminName" value="${ adminName}">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="管理员密码" required="" name="password" value="${password }">
        <div class="errorMessage" style="color:red"><p id="NameOrPdError"> ${errorMsg } </p></div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="loginSub" >登陆</button>
      </form>
    </div>
</div>
</body>
</html>