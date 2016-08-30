package zhouzhutou.news.back;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import zhouzhutou.news.util.DateUtil;
import zhouzhutou.news.util.PropertiesUtil;

public class ImageUploadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName;
    String newPath;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//boolean isMutipart=ServletFileUpload.isMultipartContent(request);
		String callback=request.getParameter("CKEditorFuncNum");
		PrintWriter out=response.getWriter();
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List<FileItem> items=upload.parseRequest(request);
			/*Iterator<FileItem> iterator=items.iterator();
			while(iterator.hasNext())
			{
				FileItem fileItem=iterator.next();
			
				    try {
					    System.out.println(fileItem.getName());
					    System.out.println(DateUtil.getCurrentDateStr());
					    String fileName=new String(DateUtil.getCurrentDateStr()+'.'+fileItem.getName().split(".")[1]);
				        File uploadedFile=new File(PropertiesUtil.getProperty("imagePos")+fileName);
				        fileItem.write(uploadedFile);
				    }catch(Exception e)
				    {
					    e.printStackTrace();
				    }
				}*/
			for(FileItem fileItem:items)
			{
				//System.out.println(fileItem);
				if(fileItem.getName()!=null)
				{
				   System.out.println(fileItem.getName());
				   fileName=DateUtil.getCurrentDateStr()+'.'+fileItem.getName().split("\\.")[1];
				   File file=new File(PropertiesUtil.getProperty("imagePos")+fileName);
				   fileItem.write(file);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newPath=PropertiesUtil.getProperty("imageURL");
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction("+callback+",'"+newPath+fileName+"','')");
		out.println("</script>");
		out.flush();
		//out.close();
	}
	

}
