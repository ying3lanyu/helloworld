package user.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import user.model.Machineinfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception{
		ResultSetMetaData rsmd = rs.getMetaData();
		int num = rsmd.getColumnCount();
		JSONArray array = new JSONArray();
		while(rs.next()){
			JSONObject obj = new JSONObject();
			for (int i = 1; i <= num; i++) {
				if(rsmd.getColumnName(i).equals("time")){
					obj.put(rsmd.getColumnName(i), rs.getObject(i).toString());
				}else{

					obj.put(rsmd.getColumnName(i), rs.getObject(i));
				}
			}
			array.add(obj);
		}
		

		return array;
	}
}
