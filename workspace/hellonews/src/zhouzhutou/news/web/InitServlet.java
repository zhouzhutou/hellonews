package zhouzhutou.news.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.sf.json.JSONObject;
import zhouzhutou.news.dao.NewsDao;
import zhouzhutou.news.dao.NewsTypeDao;
import zhouzhutou.news.model.News;
import zhouzhutou.news.model.NewsType;
import zhouzhutou.news.model.Result;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.ResponseUtil;

public class InitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private DbUtil dbUtil;
	private NewsTypeDao newsTypeDao;
	private NewsDao newsDao;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext application=getServletConfig().getServletContext(); 
		refreshSystem(application);
	}
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext application=getServletConfig().getServletContext();
		refreshSystem(application);
		JSONObject result=new JSONObject();
		Result res=new Result(true,"刷新成功");
		result=JSONObject.fromObject(res);
		try {
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refreshSystem(ServletContext application)
    {
    	dbUtil=new DbUtil();
    	newsTypeDao=new NewsTypeDao();
    	newsDao=new NewsDao();
    	Connection con=null;
    	try {
			con=dbUtil.getCon();
			String sql="select * from t_newstype";
			List<NewsType> newsTypeList=newsTypeDao.newsTypeList(con, sql);
			application.setAttribute("newsTypeList", newsTypeList);
			sql="select * from t_news order by click DESC limit 0,8";
			List<News> clickMostNewsList=newsDao.newsList(con, sql);
			application.setAttribute("clickMostNewsList", clickMostNewsList);
			sql="select * from t_news order by createAt limit 0,8";
			List<News> newestNewsList=newsDao.newsList(con, sql);
			application.setAttribute("newestNewsList", newestNewsList);
			System.out.println("刷新了");
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
