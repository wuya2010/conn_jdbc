package conn;


import utls.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * 第6步：
 * 
 * 此类用于使用增强版的Statement
 * 
 * PreparedStatement 是Statement的子接口
 * 相比较Statement而言 功能更强大  可以设置sql中的参数
 * 一般使用PrepareStatement接口
 * 
 * 
 * 增删改操作 大部分都使用PrepareStatement 
 * 因为更容易操作
 * 注：sql语句中？的索引从1开始
 * 
 * */

public class PrepareStatementTest {
	
	@Test
	public void insertTest(){
		//1.获取连接
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建PreparedStatement对象
		PreparedStatement pstmt = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入管理员姓名 : ");
		String name = scanner.nextLine();
		System.out.println("请输入管理员密码 : ");
		String password = scanner.nextLine();
		//System.out.println("请输入管理员性别 : ");
		//String sex = scanner.nextLine();
		
		//3.创建sql
		String sql = "insert into admin values(?,?)";
		
		//4.执行sql
		try {
			//这句话老师直接写出来的，要体会一下，，返回PreparedStatement对象
			//pstmt = conn.prepareStatement(sql);
			//传入的参数         就是你要执行的sql查询字符串
			pstmt = conn.prepareStatement(sql);
			
			//4.1将参数设置进sql中
			pstmt.setObject(1, name);
			pstmt.setObject(2, password);
			//pstmt.setObject(3, sex);
			
		
			//4.2执行sql，， 跟stmt是一样的，父子接口关系
			int result = pstmt.executeUpdate();
			
			System.out.println(result > 0 ? "插入成功" : "插入失败");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//5.关流
			JDBCUtils.closeAll(null,pstmt, conn);
			scanner.close();
		}
		
		
		
	}
	
	
	@Test
	public void insertTest1(){
		//1.获取连接
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建PreparedStatement对象
		PreparedStatement pstmt = null;
		
		//3.创建sql
		String sql = "insert into emp values(?,?,?,?,?,?,?)";
		
		//4.执行sql
		try {
			pstmt = conn.prepareStatement(sql);
			
			//4.1将参数设置进sql中
			pstmt.setInt(1, 1001);
			pstmt.setString(2, "azrail");
			pstmt.setDouble(3, 300);
			pstmt.setDouble(4,3);
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setObject(6, null);
			pstmt.setInt(7, 10);
			
			//4.2执行sql
			int result = pstmt.executeUpdate();
			
			System.out.println(result > 0 ? "插入成功" : "插入失败");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//5.关流
			JDBCUtils.closeAll(null,pstmt, conn);
		}
		
		
		
	}
}
