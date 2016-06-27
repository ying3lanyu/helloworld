package user.util;
	import java.sql.Connection;
	import java.sql.DriverManager;

public class DB_link {


//	public static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
//	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
//	public static final String DBUSER = "scott";
//	public static final String DBPASS = "199244";
	
//	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
//	public static final String DBURL = "jdbc:mysql://playwolf.mysql.rds.aliyuncs.com/db_test";
//	public static final String DBUSER = "dcb";
//	public static final String DBPASS = "123456";
	
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
//	public static final String DBURL = "jdbc:mysql://localhost:3306/simao00";
	public static final String DBURL = "jdbc:mysql://192.168.1.158:3306/machinedetetion";
	public static final String DBUSER = "root";
	//public static final String DBPASS = "123521"; 
	public static final String DBPASS = "123456"; 
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 * @author wzh
	 */
	public Connection getCon() throws Exception{
		//连接数据库
		Class.forName(DBDRIVER);
		Connection con=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 * @author wzh
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	public static void main(String[] args){
			
		DB_link db_link = new DB_link();
		try {
			db_link.getCon();
			System.out.println("数据库连接ok!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
