package conn;


import org.junit.Test;
import utls.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 第8步：
 * 拓展：
 * 
 * 批处理
 * 
 * 当需要大量处理相同数据时 可以一次性执行多条语句
 * 
 * */

public class BatchTest {

	@Test
	public void insertTest(){
		//获取连接
		Connection conn = JDBCUtils.getConnection();
		
		PreparedStatement pstmt = null;
		
		String sql = "insert into boys values(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 1;i<=1000;i++){
				pstmt.setInt(1, 7+i);
				pstmt.setString(2,"张伟"+i+"号"); 
				
				//添加批处理操作  addBatch（）批量操作
				pstmt.addBatch();

				if(i%100  == 0){
					//每100次处理一次
					pstmt.executeBatch();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}
