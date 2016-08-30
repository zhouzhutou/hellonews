package zhouzhutou.news.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhouzhutou.news.dao.LinkDao;
import zhouzhutou.news.dao.NewsDao;
import zhouzhutou.news.dao.NewsTypeDao;
import zhouzhutou.news.model.Link;
import zhouzhutou.news.model.News;
import zhouzhutou.news.model.NewsType;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.StringUtil;



public class IndexServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	NewsDao newsDao=new NewsDao();
	NewsTypeDao newsTypeDao=new NewsTypeDao();
	LinkDao linkDao=new LinkDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Connection con=null;
	    try {
			con=dbUtil.getCon();
			String sql="select * from t_newstype";
			List<NewsType> newsTypeList=newsTypeDao.newsTypeList(con, sql);//鑾峰彇鏂伴椈绫诲埆鍒楄〃
			
			sql="select * from t_link order by orderNum ASC";
			List<Link> linkList=linkDao.linkList(con,sql);//鑾峰彇閾炬帴鍒楄〃
			
			sql="select * from t_news where isImage=1 order by createAt DESC limit 0,5";
			List<News> imageNewsList=newsDao.newsList(con, sql);//鑾峰彇鍥剧墖鏂伴椈鍒楄〃
			
			sql="select * from t_news where isHead=1 order by createAt DESC limit 0,1";
			List<News> headNewsList=newsDao.newsList(con, sql);//鑾峰彇澶存爣棰樻柊闂�
			News headNews=headNewsList.get(0);
			headNews.setNewsContent(StringUtil.Html2Text(headNews.getNewsContent()));
			
			//sql="select * from t_news order by createAt DESC limit 0,8";
			//List<News> updateNewsList=newsDao.newsList(con, sql);//鑾峰彇鏈�繎鏇存柊鏂伴椈
			
			sql="select * from t_news where isHot=1 order by createAt DESC limit 0,8";
			List<News> hotNewsList=newsDao.newsList(con, sql);//鑾峰彇鐑棬鏂伴椈鍒楄〃
	        
			List allNewsList=new ArrayList<>();
			if(newsTypeList!=null&&newsTypeList.size()>0)
			{
			for(int i=0;i<newsTypeList.size();i++)
			{
				int newsTypeId=newsTypeList.get(i).getNewsTypeId();
				sql="select * from t_news as a where a.newsTypeId="+newsTypeId+" order by createAt DESC limit 0,8";
				List<News> oneNewsTypeList=newsDao.newsList(con, sql);
				allNewsList.add(oneNewsTypeList);
			}
			}
			
			request.setAttribute("allNewsList", allNewsList);
			//request.setAttribute("updateNewsList", updateNewsList);
			request.setAttribute("hotNewsList", hotNewsList);
			request.setAttribute("headNews", headNews);
			request.setAttribute("imageNewsList", imageNewsList);
			//request.setAttribute("newsTypeList", newsTypeList);
			request.setAttribute("linkList", linkList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
