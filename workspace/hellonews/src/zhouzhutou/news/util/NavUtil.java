package zhouzhutou.news.util;


public class NavUtil {
     public static String getNewsListNav(int newsTypeId,String newsTypeName)
     {
    	 StringBuffer sb=new StringBuffer("��ǰλ��:"
    	 		+ "<span><a href='goIndex'>��ҳ</a></span><span>/</span><span><a href='news?action=list&typeId="+newsTypeId+"'>"+newsTypeName+"</a></span>");
    	 return sb.toString();
     }
    public static String getNewsShowNav(int newsTypeId,String newsTypeName,String title)
    {
    	StringBuffer sb=new StringBuffer("��ǰλ��:"
    			+"<span><a href='goIndex'>��ҳ</a></span><span>/</span><span><a href='news?action=list&typeId="
    			+newsTypeId+"'>"+newsTypeName+"</a></span><span>/</span><span>"+title+"</span>");
    	return sb.toString();
    }
    
    public static String getManageNav(String manager,String operator)
    {
    	StringBuffer sb=new StringBuffer("��ǰλ��:"+
                        "<span>"+manager+"</span><span>/</span><span>"+operator+"</span>");
    	return sb.toString();
    }
}
