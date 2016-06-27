package user.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class RespeonseUtil {

	public static void write(HttpServletResponse response, JSONObject jsonobject) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonobject.toString());
		out.flush();
		out.close();
	}
}
