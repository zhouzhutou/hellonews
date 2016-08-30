package zhouzhutou.news.web;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.fastinfoset.sax.Properties;

import net.sf.json.JSONObject;
import zhouzhutou.news.dao.CommentDao;
import zhouzhutou.news.dao.NewsDao;
import zhouzhutou.news.dao.NewsTypeDao;
import zhouzhutou.news.model.Comment;
import zhouzhutou.news.model.DatePicker;
import zhouzhutou.news.model.News;
import zhouzhutou.news.model.NewsType;
import zhouzhutou.news.model.PageBean;
import zhouzhutou.news.model.Result;
import zhouzhutou.news.util.DateUtil;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.NavUtil;
import zhouzhutou.news.util.PageUtil;
import zhouzhutou.news.util.PropertiesUtil;
import zhouzhutou.news.util.ResponseUtil;
import zhouzhutou.news.util.StringUtil;

public class NewsServlet extends HttpServlet{
    
	private DbUtil dbUtil=new DbUtil();
	@SuppressWarnings("unused")
	private NewsTypeDao newsTypeDao=new NewsTypeDao();
	private NewsDao newsDao=new NewsDao();
	private CommentDao commentDao=new CommentDao();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action.equals("list"))
		{		
				//newsList(request,response);	
				// TODO Auto-generated catch block	
			String typeId=request.getParameter("typeId");
			News news=new News();
			//news.setNewsTypeId(-1);
			if(!StringUtil.isEmpty(typeId))
			{
				news.setNewsTypeId(Integer.parseInt(typeId));
			}
			//request.setAttribute("newsList", "/foreground/news/newsList.jsp");
			String page=request.getParameter("page");
			if(StringUtil.isEmpty(page))
			{
				page="1";
			}
			
		    PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getProperty("pageSize")));
			pageBean.setStart();
		   // String sql="select * from t_newstype where newsTypeId="+Integer.parseInt(typeId);
			Connection con=null;
			List<News> typeNewsList=new ArrayList<News>();
			NewsType oneNewsType=new NewsType();
			int total=0;
			try {
				con=dbUtil.getCon();
				typeNewsList=newsDao.typeNewsList(con, news, pageBean);
				//oneNewsType=newsTypeDao.newsTypeList(con, sql).get(0);
				oneNewsType=newsTypeDao.getNewsTypeById(con, Integer.parseInt(typeId));
				total=newsDao.getTypeNewsTotleNumber(con, news,new DatePicker());
				System.out.println(total);
				System.out.println(oneNewsType.getNewsTypeName());
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
		    String newsListPagination=PageUtil.getPagination(total, Integer.parseInt(PropertiesUtil.getProperty("pageSize")), Integer.parseInt(page),Integer.parseInt(typeId));
		    request.setAttribute("newsListPagination", newsListPagination);
			request.setAttribute("newsListNav", NavUtil.getNewsListNav(oneNewsType.getNewsTypeId(), oneNewsType.getNewsTypeName()));
			request.setAttribute("typeNewsList",typeNewsList);
			String newsList=new String("/foreground/news/newsList.jsp");
			System.out.println(newsList);
			request.setAttribute("newsVary", newsList);
			request.getRequestDispatcher("foreground/news/newsListMain.jsp").forward(request, response);
		}
		else if(action.equals("show"))
		{
			String newsId=request.getParameter("newsId");
			Connection con=null;
			News news=null;
			DbUtil dbUtil=new DbUtil();
			Comment t_comment=new Comment();
			t_comment.setNewsId(Integer.parseInt(newsId));
			List<News> upAndDownNewsList=new ArrayList<>();
			List<Comment> commentsList=new ArrayList<Comment>();
			try {
				con=dbUtil.getCon();
				newsDao.updateNewsClick(con, Integer.parseInt(newsId));
				news=newsDao.getNewsById(con, Integer.parseInt(newsId));
				upAndDownNewsList=newsDao.getUpAndDown(con, Integer.parseInt(newsId));
				commentsList=commentDao.listComments(con, t_comment);
				//System.out.println(commentsList.get(0).getUserName());
				System.out.println(commentsList.size());
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
			
			request.setAttribute("news", news);
			String newsShow=new String("/foreground/news/newsShow.jsp");
			request.setAttribute("newsVary", newsShow);
			request.setAttribute("commentsList", commentsList);
			request.setAttribute("newsShowNav", NavUtil.getNewsShowNav(news.getNewsTypeId(), news.getNewsTypeName(), news.getTitle()));
			request.setAttribute("upAndDownNewsList",upAndDownNewsList);
			String ss=NavUtil.getNewsShowNav(Integer.parseInt(newsId), news.getNewsTypeName(), news.getTitle());
			//System.out.println(NavUtil.getNewsShowNav(Integer.parseInt(newsId), news.getNewsTypeName(), news.getTitle()));
			request.getRequestDispatcher("foreground/news/newsListMain.jsp").forward(request, response);
		}else if(action.equals("preSave"))
		{
			Connection con=null;
			String newsId=request.getParameter("newsId");
			List<NewsType> newsTypeList=new ArrayList<>();
			News backNews=new News();
			try {
				con=dbUtil.getCon();
				newsTypeList=newsTypeDao.newsTypeList(con,new String("select * from t_newstype"));
				if(!StringUtil.isEmpty(newsId))
				{
					backNews=newsDao.getNewsById(con, Integer.parseInt(newsId));
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
			request.setAttribute("newsTypeList", newsTypeList);
			String mainPage=new String("/background/back/newsSave.jsp");
			request.setAttribute("mainPage", mainPage);
			if(StringUtil.isEmpty(newsId))
			{
			   request.setAttribute("navCode", NavUtil.getManageNav("新闻管理", "新闻添加"));
			   request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
			}else{
				request.setAttribute("backNews", backNews);
				request.setAttribute("navCode", NavUtil.getManageNav("新闻管理", "新闻修改"));
				request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
			}
		}else if(action.equals("save"))
		{
			News news=new News();
			DiskFileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			try {
				List<FileItem> items=upload.parseRequest(request);
				Iterator<FileItem> iter=items.iterator();
				while(iter.hasNext())
				{
					FileItem item=iter.next();
					if(item.isFormField())
					{
						String fieldName=item.getFieldName();
						if(fieldName.equals("newsId"))
						{
							String newsId=item.getString("utf-8");
							if(!StringUtil.isEmpty(newsId))
							    news.setNewsId(Integer.parseInt(newsId));
						}
						if(fieldName.equals("newsTitle"))
						{
							String title=item.getString("utf-8");
							news.setTitle(title);
						}
						if(fieldName.equals("author"))
						{
							String author=item.getString("utf-8");
							news.setAuthor(author);
						}
						if(fieldName.equals("newsTypeList"))
						{
							String newsTypeId=item.getString("utf-8");
							news.setNewsTypeId(Integer.parseInt(newsTypeId));
						}
						if(fieldName.equals("newsContent"))
						{
							String newsContent=item.getString("utf-8");
							news.setNewsContent(newsContent);
						}
						if(fieldName.equals("isHead"))
						{
							String isHead=item.getString("utf-8");
							news.setIsHead(Integer.parseInt(isHead));
						}
						if(fieldName.equals("isImage"))
						{
							String isImage=item.getString("utf-8");
							news.setIsImage(Integer.parseInt(isImage));
						}
						if(fieldName.equals("isHot"))
						{
							String isHot=item.getString("utf-8");
							news.setIsHot(Integer.parseInt(isHot));
						}
						if(fieldName.equals("preImage")&&StringUtil.isEmpty(news.getImageName()))
						{
							String preImageName=item.getString("utf-8");
							news.setImageName(preImageName);
						}
					}else if(!StringUtil.isEmpty(item.getName()))
					{						
							String fileName=DateUtil.getCurrentDateStr()+'.'+item.getName().split("\\.")[1];
							File file=new File(PropertiesUtil.getProperty("imagePos")+fileName);//写入磁盘的位置
							news.setImageName(fileName);
							item.write(file);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Connection con=null;
			JSONObject result=new JSONObject();
			try {
				con=dbUtil.getCon();
				if(news.getNewsId()==0)
				{
					int num=newsDao.newsAdd(con, news);
					if(num>0)
					{
						Result res=new Result(true,"添加成功");
						result.put("success", res);
						//System.out.println("添加成功");
					}else{
						Result res=new Result(false,"添加失败");
						result.put("success", res);
						//System.out.println("添加失败");
					}
				}else{
					int num=newsDao.newsUpdate(con, news);
					if(num>0)
					{
						Result res=new Result(true,"修改成功");
						result.put("success", res);						
					}else{
						Result res=new Result(false,"修改失败");
						result.put("success", res);
					}
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
		}else if(action.equals("maintain"))
		{
			String page=request.getParameter("page");
			String s_newsCreateAt=request.getParameter("s_newsCreateAt");
			String e_newsCreateAt=request.getParameter("e_newsCreateAt");
			String newsSearchTitle=request.getParameter("newsTitle_search");
			News news=new News();
			HttpSession session=request.getSession();
			if(StringUtil.isEmpty(page))
			{
			    page="1";	
			    session.setAttribute("s_newsCreateAt", s_newsCreateAt);
			    session.setAttribute("e_newsCreateAt", e_newsCreateAt);
			    session.setAttribute("newsTitle_search", newsSearchTitle);
			}else{
			    s_newsCreateAt=(String)session.getAttribute("s_newsCreateAt");
			    e_newsCreateAt=(String)session.getAttribute("e_newsCreateAt");
			    newsSearchTitle=(String)session.getAttribute("newsTitle_search");
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getProperty("pageBackSize")));
			pageBean.setStart();
			news.setTitle(newsSearchTitle);
			Connection con=null;
			DatePicker datePicker=new DatePicker(s_newsCreateAt,e_newsCreateAt);
			List<News> backNewsList=new ArrayList<>();
			try {
				con=dbUtil.getCon();
				int newsTotalNum=newsDao.getTypeNewsTotleNumber(con, news, datePicker);
				List<News> newsBackList=newsDao.backNewsList(con, news, pageBean, datePicker);
		        String newsBackPagination=PageUtil.getBackPagination(request.getContextPath()+"/news",
		        		Integer.parseInt(PropertiesUtil.getProperty("pageBackSize")), Integer.parseInt(page), newsTotalNum);
			    
		        request.setAttribute("newsBackPagination", newsBackPagination);
				request.setAttribute("newsBackList", newsBackList);
				request.setAttribute("navCode", NavUtil.getManageNav("新闻管理", "新闻维护"));
				String mainPage=new String("/background/back/newsMaintain.jsp");
				request.setAttribute("mainPage", mainPage);
				request.setAttribute("newsSearchTitle", newsSearchTitle);
				request.setAttribute("datePicker", datePicker);
				request.setAttribute("page", Integer.parseInt(page));
				request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
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
		}else if(action.equals("delete"))
		{
			String newsIds=request.getParameter("newsIds");
			Connection con=null;
			JSONObject result=new JSONObject();
			try {
				con=dbUtil.getCon();
				int num=newsDao.newsDelete(con, newsIds);
				if(num>0)
				{
					Result res=new Result(true,"删除成功");
					result=JSONObject.fromObject(res);
				}else{
					Result res=new Result(false,"删除失败");
					result=JSONObject.fromObject(res);
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
	   
	}

}
