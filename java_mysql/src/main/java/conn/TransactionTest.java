package conn;



import utls.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
	
	//事务的验证
	@Test
	public void insertTest() {
		Connection conn = JDBCUtils.getConnection();
		
		String sql = "insert into girls values(?,?,?,?)";
		
		PreparedStatement pstmt = null;
				
		try {
			pstmt = conn.prepareStatement(sql);
			//开启事务
			conn.setAutoCommit(false);
			
			
			pstmt.setInt(1, 10);
			pstmt.setString(2, "新垣结衣");
			pstmt.setString(3, "12345678900");
			pstmt.setObject(4, null);
			
			pstmt.executeUpdate();
			
			//模拟一个异常  当出现异常 ，值不应该存进去，这个时候就用事务了
//			System.out.println(1/0);
			
			pstmt.setInt(1, 11);
			pstmt.setString(2, "石原里美");
			pstmt.setString(3, "12345678900");
			pstmt.setObject(4, null);
			
			pstmt.executeUpdate();
			
			//如果可以执行到此处 保存数据
			conn.commit(); 
			
		} catch (Exception e) {
			//如果捕获了异常 回滚
			try {
				conn.rollback();//有异常的情况下 ， 回滚，执行不算数，取消订单
				
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
