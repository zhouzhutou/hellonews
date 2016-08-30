package zhouzhutou.news.back;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSON;
import net.sf.json.JSONObject;
import zhouzhutou.news.dao.NewsDao;
import zhouzhutou.news.dao.NewsTypeDao;
import zhouzhutou.news.model.NewsType;
import zhouzhutou.news.model.Result;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.NavUtil;
import zhouzhutou.news.util.ResponseUtil;
import zhouzhutou.news.util.StringUtil;

public class NewsTypeServlet extends HttpServlet{
    private DbUtil dbUtil=new DbUtil();
    private NewsTypeDao newsTypeDao=new NewsTypeDao();
    private NewsDao newsDao=new NewsDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		 if(action.equals("preSave"))
		    {
		    	preSave(request,response);
		    }else if(action.equals("save"))
		    {
		    	save(request,response);
		    	//System.out.println("save");
		    }else if(action.equals("maintain"))
		    {
		    	maintian(request,response);
		    }else if(action.equals("delete"))
		    {
		    	delete(request,response);
		    }
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String newsTypeId=request.getParameter("newsTypeId");
		NewsType newsType=new NewsType();
		newsType.setNewsTypeId(Integer.parseInt(newsTypeId));
		Connection con=null;
		JSONObject result=new JSONObject();
		Result res=null;
		try {
			con=dbUtil.getCon();
			boolean exist=newsDao.existNewsWithNewsTypeId(con, Integer.parseInt(newsTypeId));
			if(exist==true)
			{
				//NewsType newsTypeExist=newsTypeDao.getNewsTypeById(con, Integer.parseInt(newsTypeId));
				res=new Result(false,"无法删除");
			    result.put("success", JSONObject.fromObject(res));
			}else{
				int num=newsTypeDao.newsTypeDelete(con, newsType);
				if(num>0)
				{
					res=new Result(true,"删除成功");
					result.put("success", JSONObject.fromObject(res));
				}else{
					res=new Result(false,"删除失败");
					result.put("suceess", JSONObject.fromObject(res));
				}
			}
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
		try {
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void maintian(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String sql="select * from t_newstype order by newsTypeId";
		Connection con=null;
		List<NewsType> newsTypeList=new ArrayList<>();
		try {
			con=dbUtil.getCon();
			newsTypeList=newsTypeDao.newsTypeList(con, sql);
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
		request.setAttribute("navCode", NavUtil.getManageNav("新闻类别管理", "新闻类别维护"));
		request.setAttribute("newsTypeBackList", newsTypeList);
		String mainPage=new String("/background/back/newsTypeMaintain.jsp");
		request.setAttribute("mainPage", mainPage);
		request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
	}

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String newsTypeId=request.getParameter("newsTypeId");
		String newsTypeName=request.getParameter("newsTypeName");
		NewsType newsType=new NewsType(newsTypeName);
		JSONObject result=new JSONObject();
		if(StringUtil.isEmpty(newsTypeId))
		{
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int num=newsTypeDao.newsTypeAdd(con, newsType);
				if(num>0)
				{
					Map<String,Object> succcessMap=new HashMap<>();
					succcessMap.put("bool", true);
					succcessMap.put("message", "添加成功");
					JSONObject successObj=JSONObject.fromObject(succcessMap);
					result.put("success", successObj);
				}else{
					Map<String, Object> failMap=new HashMap<>();
					failMap.put("bool", false);
					failMap.put("message", "添加失败");
					JSONObject failObj=JSONObject.fromObject(failMap);
					result.put("suceess", failObj);
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
		}else{
			Connection con=null;
			newsType.setNewsTypeId(Integer.parseInt(newsTypeId));
			try {
				con=dbUtil.getCon();
				int num=newsTypeDao.newsTypeUpdate(con, newsType);
				if(num>0)
				{
					Map<String,Object> succcessMap=new HashMap<>();
					succcessMap.put("bool", true);
					succcessMap.put("message", "修改成功");
					JSONObject successObj=JSONObject.fromObject(succcessMap);
					result.put("success", successObj);
				}else{
					Map<String, Object> failMap=new HashMap<>();
					failMap.put("bool", false);
					failMap.put("message", "修改失败");
					JSONObject failObj=JSONObject.fromObject(failMap);
					result.put("suceess", failObj);
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
		}
		try {
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String newTypeId=request.getParameter("newsTypeId");
		if(StringUtil.isEmpty(newTypeId))
		{
			request.setAttribute("navCode", NavUtil.getManageNav("新闻类别管理", "新闻类别添加"));
			String mainPage=new String("/background/back/newsTypeSave.jsp");
			request.setAttribute("mainPage", mainPage);
			request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
		}else{
			Connection con=null;
            NewsType newsType=new NewsType(); 
			try {
				con=dbUtil.getCon();
				newsType=newsTypeDao.getNewsTypeById(con, Integer.parseInt(newTypeId));
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
			request.setAttribute("navCode", NavUtil.getManageNav("新闻类别管理", "新闻类别修改"));
			request.setAttribute("newsType", newsType);
			String mainPage=new String("/background/back/newsTypeSave.jsp");
			request.setAttribute("mainPage", mainPage);
			request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
		}
	}
    
}
