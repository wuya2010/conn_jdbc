package conn;


import utls.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * 
 * 
 * 第7-2步：
 * 
 * 
 * 此类专门测试查询sql
 * 
 * ResultSet 是代表select语句从数据库查询出的结果集
 * 必须使用           while(resultSet.next()) 方法遍历   ，如果只有一条  if(resultSet.next())
 * 
 * 注：调用过rs.next()后才可以获取数据
 * 
 * get相应类型() 方法
 * 
 * 参数传递sql中的列名
 * 
 * ResultSet是一个流 需要关闭
 * 
 * 
 * 
 * */


public class SelectTest {
	
	@Test
	public void loginTest(){
		//1.获取连接
		Connection conn = JDBCUtils.getConnection();
		
		//2.创建pstmt
		PreparedStatement pstmt = null;
		
		//3.创建sql
	/*	String sql = "select username,password from admin"
				+ " where username = ?"
				+ " and password = ?";*/
		
		
		String sql = "select * from admin";
		//
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//4.传入参数
/*			pstmt.setString(1,"牛逼");
			pstmt.setString(2,"123");
			*/
			
			//5.执行sql
			rs = pstmt.executeQuery();
			
			//5.1遍历结果集
			while(rs.next()){
				//获取数据 get方法需要列名
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				//6.获取结果
				System.out.println(username + " " + password);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//7.关流
			JDBCUtils.closeAll(rs,pstmt, conn);
		}
		
		
	}
	
	
}
