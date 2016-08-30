package zhouzhutou.news.util;

public class PageUtil {
    public static String getPagination(int total,int pageSize,int currentPage,int newsTypeId)
    {
    	int pages=total%pageSize==0 ? total/pageSize:(total/pageSize+1);
    	StringBuffer sb=new StringBuffer("<nav><ul class='pager'>");
    	if(currentPage==1)
    	{
    		sb.append("<li class='disabled'><a href='#'>上一页</a></li>");
    	}else{
    	    sb.append("<li><a href='news?action=list&typeId="+newsTypeId+"&page="+(currentPage-1)+"'>上一页</a></li>");
    	}
    	if(currentPage==pages)
    	{
    	    sb.append("<li class='disabled'><a href='#'>下一页</a></li>");
    	}
    	else{
    		sb.append("<li><a href='news?action=list&typeId="+newsTypeId+"&page="+(currentPage+1)+"'>下一页</a></li>");
    	}
    	sb.append("</ul></nav>");
    	
    	return sb.toString();
    }
    
    public static String getBackPagination(String targetURL,int pageSize,int currentPage,int totalNum)
    {
    	StringBuffer stringBuffer=new StringBuffer();
    	int pages=totalNum%pageSize==0 ? totalNum/pageSize:(totalNum/pageSize+1); 
    	stringBuffer.append("<li><a href='"+targetURL+"?action=maintain&page=1'>首页</a></li>");
    	if(currentPage==1)
    	{
    		stringBuffer.append("<li class='disabled'><a href='#'>上一页</a></li>");
    	}else{
    		stringBuffer.append("<li><a href='"+targetURL+"?action=maintain&page="+(currentPage-1)+"'>上一页</a></li>");
    	}
    	for(int i=currentPage-2;i<=currentPage+2;i++)
    	{
    		if(i<1||i>pages)
    		{
    			continue;
    		}
    		if(i==currentPage)
    		{
    		    stringBuffer.append("<li class='active'><a href='#'>"+currentPage+"</a></li>");
    		}else{
    			stringBuffer.append("<li><a href='"+targetURL+"?action=maintain&page="+i+"'>"+i+"</a></li>");	
    		}
    	}
    	if(currentPage==pages)
    	{
    		stringBuffer.append("<li class='disabled'><a href='#'>下一页</a></li>");
    	}else{
    		stringBuffer.append("<li><a href='"+targetURL+"?action=maintain&page="+(currentPage+1)+"'>下一页</a></li>");
    	}
    	stringBuffer.append("<li><a href='"+targetURL+"?action=maintain&page="+pages+"'>尾页</a></li>");
    	return stringBuffer.toString();
    }
}
