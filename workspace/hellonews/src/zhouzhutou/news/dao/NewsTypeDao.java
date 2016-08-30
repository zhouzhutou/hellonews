package zhouzhutou.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zhouzhutou.news.model.NewsType;

/**
 * NewsTypeDao绫�
 * @author zhouzhutou
 *
 */
public class NewsTypeDao {
	/**
	 * 鏂伴椈绫诲埆鏌ヨ
	 * @param con
	 * @param sql
	 * @return
	 * @throws Exception
	 */
    public List<NewsType> newsTypeList(Connection con,String sql)throws Exception
    {
    	List<NewsType> newsTypesLis=new ArrayList<NewsType>();
    	PreparedStatement ps=con.prepareStatement(sql);
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		NewsType newsType=new NewsType();
    		newsType.setNewsTypeId(rs.getInt("newsTypeId"));
    		newsType.setNewsTypeName(rs.getString("newsTypeName"));
    		newsTypesLis.add(newsType);
    	}
    	return newsTypesLis;
    }
    
    public NewsType getNewsTypeById(Connection con,int newsTypeId)throws Exception
    {
    	StringBuffer sb=new StringBuffer();
    	sb.append("select * from t_newstype");
    	if(newsTypeId!=-1)
    	{
    		sb.append(" and newsTypeId=?");
    	}
  
    	PreparedStatement ps=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
    	ps.setInt(1, newsTypeId);
    	ResultSet rs=ps.executeQuery();
    	NewsType newsType=new NewsType();
    	while(rs.next())
    	{
    		newsType.setNewsTypeId(rs.getInt("newsTypeId"));
    		newsType.setNewsTypeName(rs.getString("newsTypeName"));
    	}
    	return newsType;
    }
    
    public int newsTypeAdd(Connection con,NewsType newsType)throws Exception
    {
    	String sql="insert into t_newstype values(null,?)";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, newsType.getNewsTypeName());
    	return ps.executeUpdate();
    }
    
    public int newsTypeUpdate(Connection con,NewsType newsType)throws Exception
    {
    	String sql="update t_newstype set newsTypeName=? where newsTypeId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, newsType.getNewsTypeName());
    	ps.setInt(2, newsType.getNewsTypeId());
    	return ps.executeUpdate();
    }
    
    public int newsTypeDelete(Connection con,NewsType newsType)throws Exception
    {
    	String sql="delete from t_newstype where newsTypeId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, newsType.getNewsTypeId());
    	return ps.executeUpdate();
    }
}
