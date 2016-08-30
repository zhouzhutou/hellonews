package zhouzhutou.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zhouzhutou.news.model.Comment;
import zhouzhutou.news.model.DatePicker;
import zhouzhutou.news.model.PageBean;
import zhouzhutou.news.util.DateUtil;
import zhouzhutou.news.util.StringUtil;

public class CommentDao {
    public List<Comment> listComments(Connection con,Comment t_comment) throws Exception{
    	List<Comment> listComment=new ArrayList<Comment>();
    	StringBuffer sb=new StringBuffer("select * from t_comment");
    	if(t_comment.getNewsId()!=-1)
    	{
    		sb.append(" and newsId=?");
    	}
    	sb.append(" order by createAt limit 0,8");
    	String sql=sb.toString().replaceFirst("and", "where");
    	PreparedStatement ps=con.prepareStatement(sql);
    	if(t_comment.getNewsId()!=-1)
    	{
    		ps.setInt(1, t_comment.getNewsId());
    	}
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		Comment comment=new Comment();
    		comment.setCommentId(rs.getInt("commentId"));
    		comment.setComment(rs.getString("commentContent"));
    		comment.setNewsId(rs.getInt("newsId"));
    		comment.setCreateAt(DateUtil.formatString(rs.getString("createAt"), "yyyy-MM-dd HH:mm:ss"));
    		comment.setUserName(rs.getString("userName"));
    		listComment.add(comment);
    	}
    	return listComment;
    }
    
    public List<Comment> commentBacklist(Connection con,Comment t_comment,PageBean pageBean,DatePicker datePicker) throws Exception{
    	List<Comment> listComment=new ArrayList<Comment>();
    	StringBuffer sb=new StringBuffer("select * from t_comment t1, t_news t2 and t1.newsId=t2.newsId");
    	if(t_comment.getNewsId()!=-1)
    	{
    		sb.append(" and newsId=?");
    	}
    	if(!StringUtil.isEmpty(datePicker.getStartDate()))
    	{
    		sb.append(" and TO_DAYS(t1.createAt)>=TO_DAYS('"+datePicker.getStartDate()+"')");
    	}
    	if(!StringUtil.isEmpty(datePicker.getEndDate()))
    	{
    		sb.append(" and TO_DAYS(t1.createAt)<=TO_DAYS('"+datePicker.getEndDate()+"')");
    	}
    	sb.append(" order by t1.createAt DESC");
    	if(pageBean!=null)
    	{
    		sb.append(" limit "+pageBean.getStart()+','+pageBean.getSize());
    	}
    	String sql=sb.toString().replaceFirst("and", "where");
    	PreparedStatement ps=con.prepareStatement(sql);
    	if(t_comment.getNewsId()!=-1)
    	{
    		ps.setInt(1, t_comment.getNewsId());
    	}
    	ResultSet rs=ps.executeQuery();
    	while(rs.next())
    	{
    		Comment comment=new Comment();
    		comment.setCommentId(rs.getInt("commentId"));
    		comment.setComment(rs.getString("commentContent"));
    		comment.setNewsId(rs.getInt("newsId"));
    		comment.setTitle(rs.getString("title"));
    		comment.setCreateAt(DateUtil.formatString(rs.getString("t1.createAt"), "yyyy-MM-dd HH:mm:ss"));
    		comment.setUserName(rs.getString("userName"));
    		listComment.add(comment);
    	}
    	return listComment;
    }
    
    public int getCommentsTotalNum(Connection con,Comment comment,DatePicker datePicker)throws Exception
    {
    	StringBuffer stringBuffer=new StringBuffer("select count(*) as total from t_comment");
    	if(comment.getNewsId()!=-1)
    	{
    		stringBuffer.append(" and newsId=?");
    	}
    	if(!StringUtil.isEmpty(datePicker.getStartDate()))
    	{
    		stringBuffer.append(" and TO_DAYS(createAt)>=TO_DAYS('"+datePicker.getStartDate()+"')");
    	}
    	if(!StringUtil.isEmpty(datePicker.getEndDate()))
    	{
    		stringBuffer.append(" and TO_DAYS(createAt)<=TO_DAYS('"+datePicker.getEndDate()+"')");
    	}
    	stringBuffer.append(" order by createAt DESC");
    	PreparedStatement ps=con.prepareStatement(stringBuffer.toString().replaceFirst("and", "where"));
        if(comment.getNewsId()!=-1)
        {
        	ps.setInt(1, comment.getNewsId());
        }
    	ResultSet rs=ps.executeQuery();
    	if(rs.next())
    	{
    		return rs.getInt("total");
    	}else{
    		return 0;
    	}
    	
    }
    public int addComments(Connection con,Comment comment) throws Exception{
    	String sql="insert into t_comment values(null,?,?,now(),?)";
    	PreparedStatement ps=con.prepareStatement(sql);
    	ps.setString(1, comment.getComment());
    	ps.setInt(2, comment.getNewsId());
    	ps.setString(3, comment.getUserName());
    	return ps.executeUpdate();
    }
    
    public int commentDelete(Connection con,String commentIds)throws Exception
    {
    	String sql="delete from t_comment where commentId in("+commentIds+")";
    	PreparedStatement ps=con.prepareStatement(sql);
    	return ps.executeUpdate();
    }
    
}
