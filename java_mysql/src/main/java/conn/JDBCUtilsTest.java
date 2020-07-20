package conn;


import utls.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *第五步：
 *		对工具类进行测试
 * 
 * 测试工具类是否正常
 * 
 * 
 * */


public class JDBCUtilsTest {

	//测试建表语句
	@Test
	public void createTableTest(){
		//1.获取连接（JDBC进行连接）
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建sql
		String sql = "create table address(id int(4) primary key auto_increment,"
					+ "name varchar(20),"
					+ "pid int(4))";
		
		//3.执行sql，，，返回一个Statement对象
		Statement stmt = null;
		
		
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//4.关流（JDBC进行关流）
			JDBCUtils.closeAll(null,stmt, conn);
		}
		
	}
	
	//测试添加数据
	@Test
	public void insertTest(){
		//1.获取连接
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建Statement对象 
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			
			//3.创建sql
			String sql = "insert into address(name) values('北京')";
			
			//4.执行sql
			int result = stmt.executeUpdate(sql);
			
			System.out.println(result > 0 ? "插入数据成功" : "插入数据失败");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.关流
			JDBCUtils.closeAll(null,stmt, conn);
		}
	}
	
	//测试删除数据
	@Test
	public void deleteTest(){
		//1.获取连接
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建Statement对象 
		Statement stmt = null;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请输入要删除的人的名字");
		String name = scanner.nextLine();
		
		try {
			stmt = conn.createStatement();
			
			//3.创建sql
			String sql = "delete from girls where gname like '%" 
						+ name 
						+ "%'";
			
			//4.执行sql
			int result = stmt.executeUpdate(sql);
			
			System.out.println(result > 0 ? "删除数据成功" : "删除数据失败");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5.关流
			JDBCUtils.closeAll(null,stmt, conn);
			scanner.close();
		}
	}

	
	
}
