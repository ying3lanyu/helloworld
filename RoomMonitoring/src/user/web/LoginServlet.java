package user.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.catalina.connector.Request;

import user.dao.Userdao;
import user.model.User;
import user.util.DB_link;
import user.util.Stringutil;

public class LoginServlet extends HttpServlet {

	DB_link dblink = new DB_link();
	Userdao userdao = new Userdao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u = req.getParameter("username");
		String p = req.getParameter("password");
		req.setAttribute("username",u);
		//System.out.print(username);
		
		//用户名或密码为空
		if(Stringutil.isEmpty(u)||Stringutil.isEmpty(p)){
			//服务器端跳转
			req.setAttribute("error", "username or password can not be empty!");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			return;
		}
		
		User user= new User(u, p);
		Connection conn = null;
		try {
			conn = dblink.getCon();//连接数据库
			User currentuser = userdao.login(conn, user);
			if (currentuser== null){
				//服务器端跳转，用户名密码不合格
				req.setAttribute("error", "username or password is wrong!");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else{
				//用户名 密码合格，获取session后服务器跳转
				HttpSession session = req.getSession();
				//session.setAttribute("currentuser", currentuser);
				session.setAttribute("username", u);
				//客户端跳转
				resp.sendRedirect("monitor.jsp");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dblink.closeCon(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
