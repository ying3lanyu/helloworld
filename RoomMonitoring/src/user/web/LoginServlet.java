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
		
		//�û���������Ϊ��
		if(Stringutil.isEmpty(u)||Stringutil.isEmpty(p)){
			//����������ת
			req.setAttribute("error", "username or password can not be empty!");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			return;
		}
		
		User user= new User(u, p);
		Connection conn = null;
		try {
			conn = dblink.getCon();//�������ݿ�
			User currentuser = userdao.login(conn, user);
			if (currentuser== null){
				//����������ת���û������벻�ϸ�
				req.setAttribute("error", "username or password is wrong!");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else{
				//�û��� ����ϸ񣬻�ȡsession���������ת
				HttpSession session = req.getSession();
				//session.setAttribute("currentuser", currentuser);
				session.setAttribute("username", u);
				//�ͻ�����ת
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
