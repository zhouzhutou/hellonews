<%@page import="java.util.List"%>
<%@page import="zhouzhutou.news.model.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
//    List<News> imageNewsList=(List)request.getAttribute("imageNewsList"); 
//    int initialImageId=imageNewsList.get(0).getNewsId();
//    String initialImageName=imageNewsList.get(0).getImageName();   
%>
<%-- <%=initialImageId %> --%>
<%-- <%=initialImageName %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-wideth,initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/foreground.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function(){
    	var width=$(window).width();
    	var height=$(window).height();
    	$("body").css({
    		"width":width,
    		"height":height
    	});
    })
	var auto;
	var index=0;
	$(document).ready(function(){
		$(".hotNews_list .data_list ul li").hover(function(){
// 			$(this).
			$(this).css("background-color","#E5E5E5");
		}, function(){
			$(this).css("background-color","white");
		})
	});
	$(document).ready(function(){
		$(".lunbo_order li").hover(function(){
			clearTimeout(auto);
			index=$(this).index();
			$(this).css({"cursor":"pointer"});
			move(index);
		},function(){
			auto=setTimeout("autogo()", 3000);
		});
	});

	
	function autogo(){
		if(index<5){
			move(index);
			index++;
		}
		else{
			index=0;	
			move(index);
			index++;
		}
	}
	function move(l){
		var src=$('.tu_img').eq(index).attr('src');
		$("#fou_img").css({  "opacity": "0"  });
		$('#fou_img').attr('src',src);
		$('#fou_img').stop(true).animate({ opacity: '1'},1000);
		$('.tuhuo ul li').removeClass('fouce');
		$('.tuhuo ul li').eq(index).addClass('fouce');
		$('.tuhuo p').hide();
		$('.tuhuo p').eq(index).show();
		var ao=$('.tuhuo p').eq(index).children('a').attr('href');
		$('#fou_img').parent('a').attr("href",ao);
	}
	autogo();
	setInterval('autogo()',3000);
</script>
<title>Insert title here</title>

</head>
<body>
<div class="container">
<jsp:include page="/foreground/common/header.jsp"/>
<div class="container">

               <div class="row">
               <div class="col-md-9 col-xs-9">
                  <div>
				<DIV style="width: 330px; height: 228px;" class="tuhuo">
				<A href="news?action=show&newsId=${imageNewsList.get(0).getNewsId() }" target="_blank"><IMG style="width: 330px; height: 208px; display:block;" id="fou_img" src="${imageNewsList.get(0).getImageName() }"></A>
<!-- 					<A href="news?action=show&newsId=104">  -->
<!-- 						<IMG style="display: none;" class="tu_img" src="image/1.jpg" width="330" height="208" /> -->
<!-- 					</A> -->
<!-- 					<A href="news?action=show&newsId=103">  -->
<!-- 						<IMG style="display: none;" class="tu_img" src="image/2.jpg" width="330" height="208" /> -->
<!-- 					</A> -->
<!-- 					<A href="news?action=show&newsId=102">  -->
<!-- 						<IMG style="display: none;" class="tu_img" src="image/3.jpg" width="330" height="208" /> -->
<!-- 					</A> -->
<!-- 					<A href="news?action=show&newsId=52">  -->
<!-- 						<IMG style="display: none;" class="tu_img" src="image/two.jpg" width="330" height="208" /> -->
<!-- 					</A> -->
                <c:forEach var="imageNews" items="${imageNewsList}">
					<A href="news?action=show&newsId=${imageNews.newsId}"> 
						<IMG style="display: none;" class="tu_img" src="${imageNews.imageName}" width="330" height="208" />
					</A>
                </c:forEach>
                <c:forEach var="imageNews" items="${imageNewsList}">
			    <P style="height: 20px;" class="tc"><A href="news?action=show&newsId=${imageNews.newsId}" target="_blank" title="${imageNews.title}">${fn:substring(imageNews.title,0,24)}</A></P>
<!-- 				<P style="height: 20px;" class="tc"><A href="news?action=show&newsId=103" target="_blank" title="昆明火车站暴恐案直击:暴徒见人就砍现场混乱血腥">昆明火车站暴恐案直击:暴徒见人就砍现</A></P> -->
<!-- 				<P style="height: 20px;" class="tc"><A href="news?action=show&newsId=102" target="_blank" title="西安幼儿园被指近3年都给孩子吃过药">西安幼儿园被指近3年都给孩子吃过药</A></P> -->
<!-- 				<P style="height: 20px;" class="tc"><A href="news?action=show&newsId=52" target="_blank" title="娱乐新闻1">娱乐新闻1</A></P> -->
<!-- 				<P style="height: 20px;" class="tc"><A href="news?action=show&newsId=51" target="_blank" title="马来西亚巫师作法再寻失联飞机：这次更奇葩">马来西亚巫师作法再寻失联飞机：这次更</A></P> -->
               </c:forEach>
            <ul class="lunbo_order">
              <LI class="fouce">1</LI>
              <LI>2</LI>
              <LI>3</LI>
              <LI>4</LI>
              <LI>5</LI>
              </ul>
              </DIV>
              </div>
        <div>
             <div class="newsHeader_list">
               <div class="newsHeader">
                 <h3><a href="news?action=show&newsId=${headNews.newsId}" title="${headNews.title}">${fn:substring(headNews.title,0,16)}</a></h3>
                 <p>${fn:substring(headNews.newsContent,0,48) }...<strong><a href="news?action=show&newsId=${headNews.newsId}">[查看全文]</a></strong></p>
               </div>
               <div class="currentUpdate">
                    <h5 class="currentUpdateHeader">最近更新</h5>
                    <div class="currentUpdateDatas">
                        <table width="360px">
                        <c:forEach var="newestNews" items="${newestNewsList}" varStatus="status">
<!--                             <tr> -->
<%--                                 <td><a href="news?actoin=show&newsId=${updateNews.newsId }">${updateNews.title }</a></td>   --%>
<%--                                 <c:set > --%>
<!--                                 <td><a  href="">疯狂动物城</a></td> -->
<!--                             </tr> -->
                                <c:if test="${status.count==1 || (status.count-1)%2==0 }">
                                    <tr>
                                 </c:if>
                                    <td><a  href="news?action=show&newsId=${newestNews.newsId}" title="${newestNews.title}">${fn:substring(newestNews.title,0,14) }</a></td>
                                 <c:if test="${status.count==2 || status.count%2==0 ||status.last}">
                                    </tr>
                                 </c:if>
                         </c:forEach>
<!--                             <tr> -->
<!--                                 <td><a  href="">拉病小新</a></td>   <td><a  href="">疯狂</a></td> -->
<!--                             </tr> -->
<!--                            <tr> -->
<!--                                 <td><a  href="">LOL</a></td>   <td><a href="" >逗比的世界</a></td> -->
<!--                             </tr> -->
<!--                            <tr> -->
<!--                                 <td><a  href="">are you kidding?</a></td>   <td><a href="">来个女朋友</a></td> -->
<!--                             </tr> -->
                        </table>
                    </div>
               </div>
             </div>
             
          </div>
        </div>
        
        <div class="col-md-3 col-xs-3">
             <div class="hotNews_list">
                 <h5 class="hotNewsListHeader">热点新闻</h5>
                    <div class="data_list">
                         <ul>
                         <c:forEach var="hotNews" items="${hotNewsList }">
                         <li><a href="news?action=show&newsId=${hotNews.newsId}">${fn:substring(hotNews.title,0,16)}</a></li>
<!--                          <li><a href="#">习近平老爷子2</a></li> -->
<!--                          <li><a href="#">习近平老爷子3</a></li> -->
<!--                          <li><a href="#">习近平老爷子4</a></li> -->
<!--                          <li><a href="#">习近平老爷子5</a></li> -->
<!--                          <li><a href="#">习近平老爷子6</a></li> -->
<!--                          <li><a href="#">习近平老爷子7</a></li> -->
<!--                          <li><a href="#">习近平老爷子8</a></li>     -->
                          </c:forEach>
                         </ul>
                     </div>
             </div>
          
         </div>
     </div>   
  
         <c:forEach var="subNewsList" items="${allNewsList }" varStatus="status">
             <c:if test="${status.index%3==0 || status.first}">
                 <div class="row-fluid">
             </c:if>
                 <div class="col-md-4 col-xs-4">
                  <div class="news_list"> 
                 <c:forEach var="news" items="${subNewsList}" varStatus="subStatus">
                             <c:choose>
			                      <c:when test="${subNewsList.size()==0 }">   
			                         <div class="data_header">${newsTypeList.get(status.index).newsTypeName }<span><a href="news?action=list&typeId=${newsTypeList.get(status.index).newsTypeId }">更多...</a></span></div>
			                      </c:when> 
			                      <c:otherwise>     
				                      <c:if test="${subStatus.index==0 }">
				                      <div class="data_header">${newsTypeList.get(status.index).newsTypeName }<span><a href="news?action=list&typeId=${newsTypeList.get(status.index).newsTypeId }">更多...</a></span></div>
				                      </c:if>
				                      <div class="data_list"> 
				                          <ul>
				                               <li><span><fmt:formatDate value="${news.createAt }" pattern="yyyy.MM.dd"/></span><a href="news?action=show&newsId=${news.newsId }">${fn:substring(news.title,0,16)}</a></li>
				                          </ul>
				                      </div> 
					               </c:otherwise> 
                             </c:choose>      
                 </c:forEach>
                 </div>
                 </div>
             <c:if test="${status.index%3==2 || status.last}">
                 </div>
             </c:if>
         </c:forEach>
</div>
<jsp:include page="/foreground/common/link.jsp"/>
<jsp:include page="/foreground/common/foot.jsp"/>
<div>
</body>
</html>