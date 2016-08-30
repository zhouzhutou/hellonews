package zhouzhutou.news.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import zhouzhutou.news.dao.CommentDao;
import zhouzhutou.news.dao.NewsDao;
import zhouzhutou.news.model.Comment;
import zhouzhutou.news.model.DatePicker;
import zhouzhutou.news.model.PageBean;
import zhouzhutou.news.model.Result;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.NavUtil;
import zhouzhutou.news.util.PageUtil;
import zhouzhutou.news.util.PropertiesUtil;
import zhouzhutou.news.util.ResponseUtil;
import zhouzhutou.news.util.StringUtil;

public class CommentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommentDao commentDao=new CommentDao();
	private NewsDao newsDao=new NewsDao();
    private DbUtil dbUtil=new DbUtil();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		System.out.println(action);
		if(action.equals("addComment"))
		{
	        addComment(request,response);
	    }else if(action.equals("maintain"))
		{
	    	maintain(request,response);
		}else if(action.equals("delete"))
		{
			delete(request,response);
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String commentIds=request.getParameter("commentId");
		Connection con=null;
		JSONObject result=new JSONObject();
		try {
			con=dbUtil.getCon();
			int num=commentDao.commentDelete(con,commentIds);
			if(num>0)
			{
				Result res=new Result(true,"删除成功");
				result.put("success", res);
			}else{
				Result res=new Result(true,"删除失败");
				result.put("success", res);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void addComment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String newsId=request.getParameter("newsId");
		String commentContent=request.getParameter("comment");
        Comment comment=new Comment();
        comment.setNewsId(Integer.parseInt(newsId));
        comment.setComment(commentContent);
        comment.setUserName("zhouzhutou");
        Connection con=null;
        try {
			con=dbUtil.getCon();
			commentDao.addComments(con, comment);
			//System.out.println("aefasdfsdafsadf");
			request.getRequestDispatcher("news?action=show&newsId="+newsId).forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void maintain(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String s_commentDate=request.getParameter("s_commentDate");
		String e_commentDate=request.getParameter("e_commentDate");
		String page=request.getParameter("page");
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page))
		{
			page="1";
			session.setAttribute("s_commentDate", s_commentDate);
			session.setAttribute("e_commentDate", e_commentDate);
		}else{
			s_commentDate=(String)session.getAttribute("s_commentDate");
			e_commentDate=(String)session.getAttribute("e_commentDate");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getProperty("pageBackSize")));
		pageBean.setStart();
		DatePicker datePicker=new DatePicker(s_commentDate, e_commentDate);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int commentsTotalNum=commentDao.getCommentsTotalNum(con,new Comment(),datePicker);
			
			String commentBackPagination=PageUtil.getBackPagination(request.getContextPath()+"/comment", 
					Integer.parseInt(PropertiesUtil.getProperty("pageBackSize")), Integer.parseInt(page),commentsTotalNum);
			
			List<Comment> commentList=commentDao.commentBacklist(con, new Comment(),pageBean,datePicker);
			request.setAttribute("commentBackPagination", commentBackPagination);
			request.setAttribute("commentBackList", commentList);
			request.setAttribute("navCode", NavUtil.getManageNav("新闻评论管理", "新闻评论维护"));
			String mainPage=new String("/background/back/commentMaintain.jsp");
			request.setAttribute("mainPage", mainPage);
			request.setAttribute("datePicker", datePicker);
			request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
