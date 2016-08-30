package zhouzhutou.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.Buffer;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.ws.developer.StreamingAttachmentFeature;

import zhouzhutou.news.model.*;
import zhouzhutou.news.util.DateUtil;
import zhouzhutou.news.util.PropertiesUtil;
import zhouzhutou.news.util.StringUtil;
/**
 * NewsDao绫�
 * @author zhouzhutou
 *
 */
public class NewsDao {
	/**
	 * 鏂伴椈鏌ヨ
	 * @param con
	 * @param sql
	 * @return
	 * @throws Exception
	 */
    public List<News> newsList(Connection con,String sql)throws Exception
    {
    	List<News> newsLis=new ArrayList<News>();
    	PreparedStatement ps=con.prepareStatement(sql);
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		News news=new News();
    		news.setNewsId(rs.getInt("newsId"));
    		news.setTitle(rs.getString("title"));
    		news.setNewsContent(rs.getString("newsContent"));
    		news.setIsHead(rs.getInt("isHead"));
    		news.setIsHot(rs.getInt("isHot"));
    		news.setIsImage(rs.getShort("isImage"));
    		news.setImageName(PropertiesUtil.getProperty("imageURL")+rs.getString("imageName"));
    		news.setNewsTypeId(rs.getInt("newsTypeId"));
    		//news.setNewsTypeName(rs.getString(""));
    		news.setClick(rs.getInt("click"));
    		news.setCreateAt(DateUtil.formatString(rs.getString("createAt"),"yyyy-MM-dd HH:mm:ss"));
            newsLis.add(news);
    	}
    	return newsLis;
    }
    public int getTypeNewsTotleNumber(Connection con,News news,DatePicker datePicker) throws Exception
    {
    	StringBuffer sb=new StringBuffer("select count(*) as total from t_news");
    	if(news.getNewsTypeId()!=-1)
    	{
    		sb.append(" and newsTypeId="+news.getNewsTypeId());
    	}
    	if(!StringUtil.isEmpty(news.getTitle()))
    	{
    		sb.append(" and title like '%"+news.getTitle()+"%'");
    	}
    	if(!StringUtil.isEmpty(datePicker.getStartDate()))
    	{
    		sb.append(" and TO_DAYS(createAt)>=TO_DAYS('"+datePicker.getStartDate()+"')");
    	}
    	if(!StringUtil.isEmpty(datePicker.getEndDate()))
    	{
    		sb.append(" and TO_DAYS(createAt)<=TO_DAYS('"+datePicker.getEndDate()+"')");
    	}
    	PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=ps.executeQuery();
        int total=0;
        while(rs.next())
        {
        	 total=rs.getInt("total");
        }
    	return total;
    }
    public List<News> typeNewsList(Connection con,News news,PageBean pageBean) throws Exception
    {

    	StringBuffer sb=new StringBuffer("select * from t_news");
    	if(news.getNewsId()!=-1)
    	{
    		sb.append(" and newsTypeId="+news.getNewsTypeId());
    	}
    	/*if(!StringUtil.isEmpty(news.getTitle()))
    	{
    		sb.append(" and title like '%"+news.getTitle()+"%'");
    	}
    	if(!StringUtil.isEmpty(datePicker.getStartDate()))
    	{
    		sb.append(" and TO_DAYS(createAt)>=TO_DAYS('"+datePicker.getStartDate()+"')");
    	}
    	if(!StringUtil.isEmpty(datePicker.getEndDate()))
    	{
    		sb.append(" and TO_DAYS(createAt)<=TO_DAYS('"+datePicker.getEndDate()+"')");
    	}*/
    	if(pageBean!=null)
    	{
    		sb.append(" order by createAt DESC limit "+pageBean.getStart()+','+pageBean.getSize());
    	}
    	return newsList(con, sb.toString().replaceFirst("and", "where"));
   
    }
    
    public List<News> backNewsList(Connection con,News news,PageBean pageBean,DatePicker datePicker)throws Exception
    {
    	List<News> backNewsList=new ArrayList<>();
    	StringBuffer sb=new StringBuffer("select * from t_news t1, t_newstype t2 and t1.newsTypeId=t2.newsTypeId");
    	if(news.getNewsTypeId()!=-1)
    	{
            sb.append(" and t1.newsTypeId="+news.getNewsTypeId());
    	}
    	if(!StringUtil.isEmpty(news.getTitle()))
    	{
    		sb.append(" and t1.title like '%"+news.getTitle()+"%'");
    	}
    	if(!StringUtil.isEmpty(datePicker.getStartDate()))
    	{
    		sb.append(" and TO_DAYS(t1.createAt)>=TO_DAYS('"+datePicker.getStartDate()+"')");
    	}
    	if(!StringUtil.isEmpty(datePicker.getEndDate()))
    	{
    		sb.append(" and TO_DAYS(t1.createAt)<=TO_DAYS('"+datePicker.getEndDate()+"')");
    	}
    	if(pageBean!=null)
    	{
    		sb.append(" order by createAt DESC limit "+pageBean.getStart()+','+pageBean.getSize());
    	}
    	PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		News n=new News();
    		n.setNewsId(rs.getInt("newsId"));
    		n.setTitle(rs.getString("title"));
    		n.setNewsContent(rs.getString("newsContent"));
    		n.setCreateAt(DateUtil.formatString(rs.getString("createAt"),"yyyy-MM-dd HH:mm:ss"));
    		n.setClick(rs.getInt("click"));
    		n.setIsHead(rs.getShort("isHead"));
    		n.setIsHot(rs.getShort("isHot"));
    		n.setIsImage(rs.getShort("isImage"));
    		n.setImageName(PropertiesUtil.getProperty("imageURL")+rs.getString("imageName"));
    		n.setNewsTypeName(rs.getString("newsTypeName"));
    		n.setNewsTypeId(rs.getInt("t1.newsTypeId"));
    		n.setAuthor(rs.getString("author"));
    		backNewsList.add(n);
    	}
    	return backNewsList;
    }
    
    public News getNewsById(Connection con,int newsId)throws Exception
    {
    	StringBuffer sb=new StringBuffer("select * from t_news as t1,t_newstype as t2 "
    			+ "where t1.newsTypeId=t2.newsTypeId and t1.newsId=?");//关联查询获取news和newsType
    	PreparedStatement ps=con.prepareStatement(sb.toString());
    	ps.setInt(1, newsId);
    	ResultSet rs=ps.executeQuery();
    	News news=new News();
    	while(rs.next())
    	{
    		news.setNewsId(rs.getInt("newsId"));
    		news.setTitle(rs.getString("title"));
    		news.setNewsContent(rs.getString("newsContent"));
    		news.setCreateAt(DateUtil.formatString(rs.getString("createAt"),"yyyy-MM-dd HH:mm:ss" ));
    		news.setClick(rs.getInt("click"));
    		news.setIsHead(rs.getInt("isHead"));
    		news.setAuthor(rs.getString("author"));
    		news.setIsHot(rs.getInt("isHot"));
    		news.setIsImage(rs.getInt("isImage"));
    		news.setImageName(PropertiesUtil.getProperty("imageURL")+rs.getString("imageName"));
    		news.setNewsTypeId(rs.getInt("newsTypeId"));
    		news.setNewsTypeName(rs.getString("newsTypeName"));
    	}
    	return news;
    }
    
    public void updateNewsClick(Connection con,int newsId) throws Exception
    {
    	String sql="update t_news set click=click+1 where newsId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, newsId);
    	ps.executeUpdate();
    }
    
    public List<News> getUpAndDown(Connection con,int newsId)throws Exception
    {
    	List<News> upAndDownNewsList=new ArrayList<News>();
    	StringBuffer sbUp=new StringBuffer("select * from t_news where newsId<? order by newsId DESC limit 1");
    	PreparedStatement ps=con.prepareStatement(sbUp.toString());
    	ps.setInt(1, newsId);
    	ResultSet rs=ps.executeQuery();
    	if(rs.next())
    	{
    		upAndDownNewsList.add(new News(rs.getInt("newsId"),rs.getString("title")));
    	}else {
			upAndDownNewsList.add(new News(-1,""));
		}
    	
    	StringBuffer sbDown=new StringBuffer("select * from t_news where newsId>? order by newsId ASC limit 1");
    	ps=con.prepareStatement(sbDown.toString());
    	ps.setInt(1, newsId);
    	rs=ps.executeQuery();
    	if(rs.next())
    	{
    		upAndDownNewsList.add(new News(rs.getInt("newsId"),rs.getString("title")));
    	}else {
			upAndDownNewsList.add(new News(-1,""));
		}
    	return upAndDownNewsList;
    }
    
    public boolean existNewsWithNewsTypeId(Connection con,int newTypeId)throws Exception
    {
    	String sql="select * from t_news where newsTypeId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, newTypeId);
    	ResultSet rs=ps.executeQuery();
    	if(rs.next())
    	{
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public int newsAdd(Connection con,News news)throws Exception
    {
    	String sql="insert into t_news values(null,?,?,?,now(),0,?,?,?,?,?)";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, news.getTitle());
    	ps.setString(2, news.getNewsContent());
    	ps.setInt(3, news.getNewsTypeId());
    	ps.setInt(4, news.getIsHead());
    	ps.setInt(5, news.getIsHot());
    	ps.setInt(6, news.getIsImage());
    	ps.setString(7, news.getImageName());
    	ps.setString(8, news.getAuthor());
    	return ps.executeUpdate();
    }
    
    public int newsUpdate(Connection con,News news)throws Exception
    {
    	String sql="update t_news set title=?, newsContent=?, newsTypeId=?, isHead=?, isHot=?, isImage=?, imageName=?, author=? where newsId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, news.getTitle());
    	ps.setString(2, news.getNewsContent());
    	ps.setInt(3, news.getNewsTypeId());
    	ps.setInt(4, news.getIsHead());
    	ps.setInt(5, news.getIsHot());
    	ps.setInt(6, news.getIsImage());
    	ps.setString(7, news.getImageName());
    	ps.setString(8, news.getAuthor());
    	ps.setInt(9, news.getNewsId());
    	return ps.executeUpdate();
    }
    
    public int newsDelete(Connection con, String newsIds)throws Exception
    {
    	String sql="delete from t_news where newsId in ("+newsIds+")";
    	PreparedStatement ps=con.prepareStatement(sql);
    	return ps.executeUpdate();
    }
}
