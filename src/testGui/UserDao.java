package testGui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import testGui.DbUtil;
public class UserDao {
	
	private static Connection conn=null;
    private static PreparedStatement ps=null;
    private static ResultSet rs=null;
    private static DbUtil Dbu = new DbUtil();
	//添加用户
	public static int userAdd(Connection con, User user) throws Exception {
		String sql = "insert into user(user_id,user_name,sex,work_address,phone,`E-mail`) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getUserName());
		pstmt.setString(3, user.getSex());
		pstmt.setString(4, user.getWorkSddress());
		pstmt.setString(5, user.getPhone());
		pstmt.setString(6, user.getMail());
		return pstmt.executeUpdate();
	}
	
	//获得用户列表
	public static List<User> userList() throws Exception {
		String sql="select user_id,user_name,sex,work_address,phone,`E-mail` from user";
        List<User> list=new ArrayList<User>();
        try {
             conn = DbUtil.getCon(); 
             ps=conn.prepareStatement(sql);
             rs=ps.executeQuery();
             while(rs.next()){
                 User user=new User();
                 user.setUserId(rs.getString(1));
                 user.setUserName(rs.getString(2));
                 user.setSex(rs.getString(3));
                 user.setWorkSddress(rs.getString(4));
                 user.setPhone(rs.getString(5));
                 user.setMail(rs.getString(6));
                 list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
	
	//查询用户信息通过姓名
	public static  List<User> userSearchByUserName(String userName) throws Exception {
		String sql="select user_id,user_name,sex,work_address,phone,`E-mail` from user where user_name like ?";
        List<User> list=new ArrayList<User>();
        try {
             conn = DbUtil.getCon(); 
             ps=conn.prepareStatement(sql);
             ps.setString(1, userName);
             ps.setString(1, "%" + userName + "%" );
             rs=ps.executeQuery();
             while(rs.next()){
                 User user=new User();
                 user.setUserId(rs.getString(1));
                 user.setUserName(rs.getString(2));
                 user.setSex(rs.getString(3));
                 user.setWorkSddress(rs.getString(4));
                 user.setPhone(rs.getString(5));
                 user.setMail(rs.getString(6));
                 list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
		
		//查询用户信息通过学号
	public static  List<User> userSearchByUserId(String userId) throws Exception {
		String sql="select user_id,user_name,sex,work_address,phone,`E-mail` from user where user_id like ?";
	    List<User> list=new ArrayList<User>();
	    try {
	            conn = DbUtil.getCon(); 
	            ps=conn.prepareStatement(sql);
	            ps.setString(1, userId);
	            ps.setString(1, "%" + userId + "%" );
	            rs=ps.executeQuery();
	            while(rs.next()){
	                User user=new User();
	                user.setUserId(rs.getString(1));
	                user.setUserName(rs.getString(2));
	                user.setSex(rs.getString(3));
	                user.setWorkSddress(rs.getString(4));
	                user.setPhone(rs.getString(5));
	                user.setMail(rs.getString(6));
	                list.add(user);
	            }
	       } catch (SQLException e) {
	            e.printStackTrace();
	       }
	    return list;
	}
	
	//查询用户信息通过姓名
	public static  List<User> userSearchByUserPhoneNum(String phone) throws Exception {
		String sql="select user_id,user_name,sex,work_address,phone,`E-mail` from user where phone like ?";
        List<User> list=new ArrayList<User>();
        try {
             conn = DbUtil.getCon(); 
             ps=conn.prepareStatement(sql);
             ps.setString(1, phone);
             ps.setString(1, "%" + phone + "%" );
             rs=ps.executeQuery();
             while(rs.next()){
                 User user=new User();
                 user.setUserId(rs.getString(1));
                 user.setUserName(rs.getString(2));
                 user.setSex(rs.getString(3));
                 user.setWorkSddress(rs.getString(4));
                 user.setPhone(rs.getString(5));
                 user.setMail(rs.getString(6));
                 list.add(user);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
	
	//删除用户
	public int userDelete(Connection con, String userId) throws Exception {
		String sql = "delete from user where user_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		return pstmt.executeUpdate();
	}

	//更新用户信息
	public int userModify(Connection con, User user) throws Exception {
			String sql = "update user set user_name = ?,sex = ?,work_address = ?,phone = ?,`E-mail` = ? where user_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getSex());
			pstmt.setString(3, user.getWorkSddress());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getMail());
			pstmt.setString(6, user.getUserId());
			return pstmt.executeUpdate();
		}
}
