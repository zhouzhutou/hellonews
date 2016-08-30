/**
 * 
 */

var getCurrentTime=function(){
	var myDate=new Date();
	var weeks=['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
	var year=myDate.getFullYear();
	var month=myDate.getMonth()+1;
	var day=myDate.getDate();
	var week=weeks[myDate.getDay()];
	var hours=myDate.getHours();
	var minutes=myDate.getMinutes();
	if(minutes>=0&&minutes<=9)  
		minutes='0'+minutes;
	var seconds=myDate.getSeconds();
	if(seconds>=0&&seconds<=9)
		seconds='0'+seconds;
	var str=year+'年'+month+'月'+day+'日'+'  '+week+'  '+hours+':'+minutes+':'+seconds;
	$(".headSegThree span").first().text(str);
	setTimeout("getCurrentTime()",1000);
}
getCurrentTime();

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
