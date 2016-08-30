package zhouzhutou.news.back;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import zhouzhutou.news.dao.LinkDao;
import zhouzhutou.news.model.Link;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.NavUtil;
import zhouzhutou.news.util.ResponseUtil;
import zhouzhutou.news.util.StringUtil;

public class LinkServlet extends HttpServlet{
    
	DbUtil dbUtil=new DbUtil();
	LinkDao linkDao=new LinkDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setCharacterEncoding("utf-8");
	    String action=request.getParameter("action");
	    if(action.equals("preSave"))
	    {
	    	preSave(request,response);
	    }else if(action.equals("save"))
	    {
	    	save(request,response);
	    	System.out.println("save");
	    }else if(action.equals("maintain"))
	    {
	    	maintian(request,response);
	    }else if(action.equals("delete"))
	    {
	    	delete(request,response);
	    }
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String linkId=request.getParameter("linkId");
		Link link=new Link();
		link.setLinkId(Integer.parseInt(linkId));
		Connection con=null;
		JSONObject result=new JSONObject();
		String successStr="{\"bool\":true,\"message\":\"删除成功\"}";
		String failStr="{\"bool\":true,\"message\":\"删除失败\"}";
		try {
			con=dbUtil.getCon();
			int num=linkDao.linkDelete(con, link);
			if(num>0)
			{
				JSONObject successObj=JSONObject.fromObject(successStr);
				System.out.println(successObj.get("message"));
			    result.put("success", successObj);
			}else{
				JSONObject failObj=JSONObject.fromObject(failStr);
				System.out.println(failObj.get("message"));
				result.put("fail", failObj);
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
			System.out.println(result.get("success"));
			ResponseUtil.write(result,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void maintian(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		List<Link> linkList=new ArrayList<>();
		try {
			con=dbUtil.getCon();
			String sql="select * from t_link order by orderNum";
			linkList=linkDao.linkList(con, sql);
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
		request.setAttribute("linkBackList", linkList);
		request.setAttribute("navCode", NavUtil.getManageNav("友情链接管理","友情链接维护"));
		String mainPage=new String("/background/back/linkMaintain.jsp");
		request.setAttribute("mainPage", mainPage);
		request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
	}

	protected void save(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		// TODO Auto-generated method stub
		String linkName=request.getParameter("linkName");
		String linkAddr=request.getParameter("linkAddr");
		String linkEmail=request.getParameter("linkEmail");
		String orderNum=request.getParameter("orderNum");
		String linkId=request.getParameter("linkId");
		JSONObject result=new JSONObject();
		if(StringUtil.isEmpty(orderNum))
		{
			orderNum="1";
		}
		Link link=new Link(linkName,linkAddr,linkEmail,Integer.parseInt(orderNum));
		Connection con=null;
		if(StringUtil.isEmpty(linkId))
		{	
			//添加操作
		    try {
			    con=dbUtil.getCon();
			    int num=linkDao.linkAdd(con, link);
			    if(num>0)
			    {
			    	String successStr="{\"bool\":true,\"message\":\"添加成功\"}";
			    	JSONObject successObj=JSONObject.fromObject(successStr);
			    	result.put("success",successObj);
			    }else{
			    	String failStr="{\"bool\":false,\"message\":\"添加失败\"}";
			    	JSONObject failObj=JSONObject.fromObject(failStr);
			    	result.put("fail",failObj);
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
			//修改操作
			link.setLinkId(Integer.parseInt(linkId));
			try {
				con=dbUtil.getCon();
				int num=linkDao.linkUpdate(con, link);
				if(num>0)
				{
					String successStr="{\"bool\":true,\"message\":\"修改成功\"}";
			    	JSONObject successObj=JSONObject.fromObject(successStr);
			    	result.put("success",successObj);
				}else{
					String failStr="{\"bool\":false,\"message\":\"修改失败\"}";
			    	JSONObject failObj=JSONObject.fromObject(failStr);
			    	result.put("fail",failObj);
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
		}
		//request.getRequestDispatcher("link?action=preSave").forward(request, response);
		//输出结果的JSON字符串
		try {
			ResponseUtil.write(result, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void preSave(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		// TODO Auto-generated method stub
		String linkId=request.getParameter("linkId");
		if(StringUtil.isEmpty(linkId))
		{
			request.setAttribute("navCode", NavUtil.getManageNav("友情链接管理", "友情链接添加"));
			String mainPage=new String("/background/back/linkSave.jsp");
			request.setAttribute("mainPage",mainPage);
			request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
		}else{
			Link link=new Link();
			Connection con=null;
			try {
				con=dbUtil.getCon();
				link=linkDao.getLinkById(con, Integer.parseInt(linkId));
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
			request.setAttribute("navCode", NavUtil.getManageNav("友情链接管理", "友情链接修改"));
			request.setAttribute("link", link);
			String mainPage=new String("/background/back/linkSave.jsp");
			request.setAttribute("mainPage",mainPage);
			request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}