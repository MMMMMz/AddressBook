package testGui;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * JDBC工具类
 * */
public class DbUtil {
	private static String dbUrl = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8";

	private static String dbUserName = "root";

	private static String dbUserPassword = "root";

	private static String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 获得JDBC链接
	 * 
	 * @return、
	 * @throws Exception
	 */
	public static Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName,
				dbUserPassword);
		return con;
	}

	/**
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}