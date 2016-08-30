package zhouzhutou.news.back;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zhouzhutou.news.dao.AdminDao;
import zhouzhutou.news.model.Admin;
import zhouzhutou.news.util.DbUtil;
import zhouzhutou.news.util.StringUtil;


public class AdminPassportServlet extends HttpServlet{
    private DbUtil dbUtil=new DbUtil();
    private Admin admin=new Admin();
    private AdminDao adminDao=new AdminDao();
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("utf-8");
		if(request.getParameter("action")!=null)
		{
			if(request.getParameter("action").equals("login"))
			{
		        loginAdmin(request,response);
			}else if(request.getParameter("action").equals("logout"))
			{
				HttpSession session=request.getSession();
				session.invalidate();
				response.sendRedirect("adminPassport");
			}
		}else
		{
			request.getRequestDispatcher("/background/login/adminLogin.jsp").forward(request, response);
		}
	}

	public void loginAdmin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminName=request.getParameter("adminName");
		String password=request.getParameter("password");
		Admin admin=new Admin(adminName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			Admin adm=adminDao.findAdmin(con, admin);
			if(adm==null)
			{  
				String errorMsg=new String("*用户名或密码输入错误");
				request.setAttribute("adminName",adminName );
				request.setAttribute("password", password);
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("/background/login/adminLogin.jsp").forward(request, response);
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", adm);
			    System.out.println(adm.getPassword());
			    String string=new String("/background/back/default.jsp");
			    request.setAttribute("mainPage",string);
			    request.getRequestDispatcher("/background/back/back.jsp").forward(request, response);
			}
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

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
    
	
}
