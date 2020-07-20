package other;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 课程讲解第一步
 * 
 * 
 * jdbc : java database connection  java连接数据库
 * 
 * 
 * 此类用于展示最基础的jdbc代码
 * 
 * 加载驱动 需要相关jar包的driver名称
 * 创建连接时 需要相应数据库的url username password
 * 
 * 	连接的接口：Connection      -----------> java.sql.Connection
 *  执行sql的接口 : Statement   -----------> java.sql.Statement
 *  
 *  	Statement 含有三个方法 :
 *  			executeQuery 用于DQL
 *  			executeUpdate 用于DML
 *  			execute       用于其他的SQL
 *  
 *  使用完毕后 通常需要关流 按照从小到大的顺序关闭
 * 	ResultSet    Statement    Connection
 * 
 * */



public class TestJDBC {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/love";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			//com.mysql.jdbc.Driver在jar包中的路径
			//1.加载驱动---目的，，，认识mysql-connector- java的jar包
			Class.forName(DRIVER);
			
			//2.创建一个数据库连接对象
			//需要三个参数url user password
			//url:jdbc:mysql://localhost:3306/love   连接指向的地址（love：数据库名字）
			//user:root
			//password:root
			connection =
					DriverManager.getConnection(URL, USER, PASSWORD);
			
			
			//3.创建statement对象  ，这个对象用于执行sql语句
			//Statement 是 Java 执行数据库操作的一个重要方法，
			//用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句
			
			stmt = connection.createStatement();//返回一个Statement的对象
			
			//4.执行sql的方法
			//DQL 有返回结果 executeQuery（查询）
			//DML 只返回改变了多少行 executeUpdate（增删改）
			//其他SQL execute
			//stmt.execute("insert into boys values(6,'绿帽侠')");
			//失败时返回-1 成功时 返回影响了几行 > 0
			int result = stmt.executeUpdate("insert into girls values(10,'凤姐','110120119114',null)");
		
			System.out.println(result > 0 ? "成功" : "失败");
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//5.关流
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
}
