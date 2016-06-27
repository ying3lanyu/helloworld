package user.dao;

import java.sql.*;
import java.util.*;

import net.sf.json.JSONArray;
import user.model.Machineinfo;
import user.util.*;

public class MachineinfoDao {
	private Statement sm = null;
	private ResultSet rs = null;
	private Connection ct = null;
	private int pageCount = 0;
	private int rowCount = 0;
	private int pageSize = 15;

	public int getPageCount() {
		try {
			ct = new DB_link().getCon();//数据库连接
			sm=ct.createStatement();
			rs = sm.executeQuery("select count(*) from machineinfo");
			if (rs.next()) {
				rowCount = rs.getInt(1);//行数，int
			}
			if (rowCount % pageSize == 0) {//分页数目
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return pageCount;
	}

	public void close() { // 关闭各种打开的资源
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
		} catch (Exception e) {
			e.printStackTrace();// 打印异常，以便修改
		}
	}

	public ArrayList getUsersByPage(int pageNow) {
		ArrayList al = new ArrayList();
		try {
			ct = new DB_link().getCon();
			sm = ct.createStatement();
//			rs = sm.executeQuery("select top " + pageSize
//					+ " * from users where userId not in (select top "
//					+ pageSize * (pageNow - 1) + " userId from users) ");
			rs = sm.executeQuery("select * from machineinfo limit "+(pageSize*(pageNow-1))+","+"3");
			while (rs.next()) {

				Machineinfo minfo=new Machineinfo();
				minfo.setID(rs.getInt(1));
				minfo.setTime(rs.getString(2));
				minfo.setTemperature(rs.getString(3));
				minfo.setHumidity(rs.getString(4));
				minfo.setSmog(rs.getString(5));
				minfo.setPower(rs.getString(6));
				minfo.setWaterlog(rs.getString(7));
				al.add(minfo);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}

	public JSONArray getJsonUsersByPage(int pageNow) {
		JSONArray jsonArray=new JSONArray();
		try {
			ct = new DB_link().getCon();
			sm = ct.createStatement();
//			rs = sm.executeQuery("select top " + pageSize
//					+ " * from users where userId not in (select top "
//					+ pageSize * (pageNow - 1) + " userId from users) ");
			rs = sm.executeQuery("select * from machineinfo limit "+(pageSize*(pageNow-1))+","+pageSize);
//			rs = sm.executeQuery("select * from machineinfo");
			jsonArray=JsonUtil.formatRsToJsonArray(rs);
//			System.out.println(jsonArray);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return jsonArray;
	}
	public JSONArray getJsonUsersForAna(int pageNow) {
		JSONArray jsonArray=new JSONArray();
		try {
			ct = new DB_link().getCon();
			sm = ct.createStatement();
//			rs = sm.executeQuery("select top " + pageSize
//					+ " * from users where userId not in (select top "
//					+ pageSize * (pageNow - 1) + " userId from users) ");
			rs = sm.executeQuery("select * from machineinfo order by id desc limit 100");
//			rs = sm.executeQuery("select * from machineinfo");
			jsonArray=JsonUtil.formatRsToJsonArray(rs);
//			System.out.println(jsonArray);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return jsonArray;
	}
	public JSONArray getJsonUsersByOne(){
		JSONArray jsonArrayone=new JSONArray();
		try{
			ct = new DB_link().getCon();
			sm = ct.createStatement();
	//		rs = sm.executeQuery("select * from machineinfo where id=1");
			rs = sm.executeQuery("select * from machineinfo where id=(select max(id) from machineinfo)");
			jsonArrayone=JsonUtil.formatRsToJsonArray(rs);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonArrayone;
	}
	public Machineinfo getUsersByOne() {
		Machineinfo minfo=new Machineinfo();
		try {
			ct = new DB_link().getCon();
			sm = ct.createStatement();
		//	rs = sm.executeQuery("select * from machineinfo where id=1");
			rs = sm.executeQuery("select * from machineinfo where id=(select max(id) from machineinfo)");
			while(rs.next()){
				minfo.setID(rs.getInt(1));
				minfo.setTime(rs.getString(2));
				minfo.setTemperature(rs.getString(3));
				minfo.setHumidity(rs.getString(4));
				minfo.setSmog(rs.getString(5));
				minfo.setPower(rs.getString(6));
				minfo.setWaterlog(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
			return minfo;
	}

	public boolean checkUser(String u, String p) {
		boolean b = false;
		try {
			ct = new DB_link().getCon();// 获取ConnDB中的方法，操作数据库
			sm = ct.createStatement();
			rs = sm.executeQuery("select password from login where name='"
					+ u + "'");
			if (rs.next()) {
				if (rs.getString(1).equals(p)) {
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭各种打开的资源，释放内存
			this.close();
		}
		return b;
	}

}

//
//public class MachineinfoDao {
//
//	public ResultSet basicdata (Connection conn , PageBean pagebean , Machineinfo machineinfo) throws Exception{
//		StringBuffer sb = new StringBuffer("SELECT * FROM");
//		boolean r = true;
//		if(Stringutil.isNotEmpty(machineinfo.getID())){
//			sb.append(" machineinfo and id like '%"+machineinfo.getID()+"%'");
//			r=false;
//		}
//		if (r==true&&pagebean!=null){
//			sb.append(" (select * from (SELECT @rownum:=@rownum+1 as r, t.* FROM (SELECT @rownum:=0) r,machineinfo t) td where td.r <= "
//					+ (pagebean.getStart() + pagebean.getRows()) + ") t2 where t2.r >= " + pagebean.getStart() + " ");
//			//sb.append(" (SELECT ROWNUM r,t1.* From outbusinfo t1 where rownum <= "+(pagebean.getStart()+pagebean.getRows())+") t2 where t2.r >= "+pagebean.getStart()+" ");
//			//sb.append(" (select * from(SELECT ROWNUM r,t1.* From outbusinfo t1 where rownum <= "+(pagebean.getStart()+pagebean.getRows())+") t2 where t2.r >= "+pagebean.getStart()+" ");
//			//sb.append(" (select * from (SELECT @rownum:=@rownum+1 as ROWNUM, t.* FROM (SELECT @rownum:=0) r,outbusinfo t) td where td.ROWNUM <= "+ (pagebean.getStart() + pagebean.getRows()) + ") t2 where t2.r >= " + pagebean.getStart() + " ");
//			//sb.append(" (select * from (SELECT @rownum:=@rownum+1 as r, t.* FROM (SELECT @rownum:=0) r,outbusinfo t) td where td.ROWNUM <= "+ (pagebean.getStart() + pagebean.getRows()) + ") t2 where t2.r >= " + pagebean.getStart() + " ");
//		}
//		String str = sb.toString().replaceFirst("and", "where");
//		//System.out.println("str:"+str);
//		
//		PreparedStatement pstmt = conn.prepareStatement(str);
//		return pstmt.executeQuery();
//	
//	}
//	
//	public int  basicdataCount (Connection conn , Machineinfo machineinfo) throws Exception{
//		StringBuffer sb = new StringBuffer("select count(*) as total from machineinfo");
//		if(Stringutil.isNotEmpty(machineinfo.getID())){
//			sb.append(" machineinfo and id like '%"+machineinfo.getID()+"%'");
//		}
//		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		ResultSet rs = pstmt.executeQuery();
//		if(rs.next()){
//			return rs.getInt("total");
//		}else{
//			return 0;
//		}
//			
//	}
//	
////public int businfoUpdate (Connection conn , BasicBusinfo basicbusinfo) throws Exception{
////		
////		String sql = "update outbusinfo set busline=?,bustem=?,bushumidity=?,busspeed=?,latitude=?,longitude=?,busdate=?,bustime=?,buslight=?,nodeid=? where busid=?";
////		PreparedStatement pstmt = conn.prepareStatement(sql);
////		pstmt.setString(1, basicbusinfo.getBusline());
////		pstmt.setString(2, basicbusinfo.getBustem());
////		pstmt.setString(3, basicbusinfo.getBushumidity());
////		pstmt.setString(4, basicbusinfo.getBusspeed());
////		pstmt.setString(5, basicbusinfo.getLatitude());
////		pstmt.setString(6, basicbusinfo.getLongitude());
////		pstmt.setString(7, basicbusinfo.getBusdate());
////		pstmt.setString(8, basicbusinfo.getBustime());
////		pstmt.setString(9, basicbusinfo.getBuslight());
////		pstmt.setString(10, basicbusinfo.getNodeid());
////		pstmt.setString(11, basicbusinfo.getBusid());
////		
////		System.out.println(sql);
////		return pstmt.executeUpdate();
////	}
//  }
