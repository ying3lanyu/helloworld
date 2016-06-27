package user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import user.model.*;
import user.util.JsonUtil;
import user.util.RespeonseUtil;
import user.dao.MachineinfoDao;

public class MachineinfoJsonServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String s_pageNow=request.getParameter("pageNow");
		if(s_pageNow==null||s_pageNow.equals("") ){
			s_pageNow="1";
		} 


		try{
			int pageNow=Integer.parseInt(s_pageNow);
			MachineinfoDao minfo=new MachineinfoDao();
			//ÔÚÌø×ªµ½main¡£jsp
//			 Machineinfo bl=minfo.getUsersByOne();
			 int pageCount=minfo.getPageCount();
			 
			 JSONObject results = new JSONObject();
			 results.put("al", minfo.getJsonUsersByPage(pageNow));
//			 results.put("new",bl);
			 results.put("pageCount",pageCount);
			RespeonseUtil.write(response, results);
			 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
//package user.web;
//
//import java.io.IOException;
//import java.sql.Connection;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import user.dao.MachineinfoDao;
//import user.model.Machineinfo;
//import user.model.PageBean;
//import user.util.DB_link;
//import user.util.JsonUtil;
//import user.util.RespeonseUtil;
//
//public class MachineinfoServlet extends HttpServlet {
//
//	DB_link dblink = new DB_link();
//	MachineinfoDao machineinfoDao = new MachineinfoDao();
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//		throws ServletException, IOException {
//		this.doPost(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//		throws ServletException, IOException {
//		
//		String page = req.getParameter("page");
//		String rows = req.getParameter("rows");
//		System.out.println("page:"+page);
//		System.out.println("rows:"+rows);
//		String id = req.getParameter("id");
//		System.out.println("id:"+id);
//		if(id==null){
//			id="";
//		}
//		Machineinfo machineinfo = new Machineinfo();
//		machineinfo.setID(id);
//		PageBean pagebean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
//		Connection conn = null;
//		try {
//			conn = dblink.getCon();
//			JSONObject results = new JSONObject();
//			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(machineinfoDao.basicdata(conn, pagebean,machineinfo));
//			System.out.println("-------------");
//			System.out.println(jsonArray);
//			
//			int total = machineinfoDao.basicdataCount(conn,machineinfo);
//
//			System.out.println(total);
//			results.put("rows", jsonArray);
//			results.put("total", total);
//			RespeonseUtil.write(resp, results);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				dblink.closeCon(conn);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	
//}
