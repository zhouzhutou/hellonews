package zhouzhutou.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zhouzhutou.news.model.*;

/**
 * LinkDao绫�
 * @author zhouzhutou
 *
 */
public class LinkDao {
	/**
	 * 閾炬帴鏌ヨ
	 * @param con
	 * @param sql
	 * @return
	 * @throws Exception
	 */
    public List<Link> linkList(Connection con,String sql)throws Exception
    {
    	List<Link> linkLis=new ArrayList<Link>();
    	PreparedStatement ps=con.prepareStatement(sql);
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		Link link=new Link();
    		link.setLinkId(rs.getInt("linkId"));
    		link.setLinkName(rs.getString("linkName"));
    		link.setLinkAddr(rs.getString("linkAddr"));
    		link.setEmail(rs.getString("email"));
    		link.setOrderNum(rs.getInt("orderNum"));
    		linkLis.add(link);
    	}
    	return linkLis;
    }
    /**
     * 添加链接
     * @param con
     * @param link
     * @return
     * @throws Exception
     */
    public int linkAdd(Connection con,Link link)throws Exception{
    	String sql="insert into t_link values(null,?,?,?,?)";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, link.getLinkName());
    	ps.setString(2, link.getLinkAddr());
    	ps.setString(3, link.getEmail());
    	ps.setInt(4,link.getOrderNum());
    	return ps.executeUpdate();
    }
    /**
     * 更新链接
     * @param con
     * @param link
     * @return
     * @throws Exception
     */
    public int linkUpdate(Connection con,Link link)throws Exception{
    	String sql="update t_link set linkName=?,linkAddr=?,email=?,orderNum=? where linkId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, link.getLinkName());
    	ps.setString(2, link.getLinkAddr());
    	ps.setString(3, link.getEmail());
    	ps.setInt(4, link.getOrderNum());
    	ps.setInt(5, link.getLinkId());
    	return ps.executeUpdate();
    }
    /**
     * 删除链接
     * @param con
     * @param link
     * @return
     * @throws Exception
     */
    public int linkDelete(Connection con,Link link)throws Exception{
    	String sql="delete from t_link where linkId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, link.getLinkId());
    	return ps.executeUpdate();
    }
    /**
     * 根据id获取链接
     * @param con
     * @param linkId
     * @return
     * @throws Exception
     */
    public Link getLinkById(Connection con,int linkId)throws Exception{
    	String sql="select * from t_link where linkId=?";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setInt(1, linkId);
    	ResultSet rs=ps.executeQuery();
    	Link link=new Link();
    	while(rs.next())
    	{
    		link.setLinkId(rs.getInt("linkId"));
    		link.setLinkName(rs.getString("linkName"));
    		link.setLinkAddr(rs.getString("linkAddr"));
    		link.setEmail(rs.getString("email"));
    		link.setOrderNum(rs.getInt("orderNum"));
    	}
    	return link;
    }
}
