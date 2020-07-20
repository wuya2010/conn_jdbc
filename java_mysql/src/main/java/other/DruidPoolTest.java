package other;


import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;

import java.sql.Connection;

import java.sql.Statement;

/**
 * 第二步：
 * 由JDBC引入：  JDBC需要频繁创建调用数据库，很消耗内存，
 * 				通过连接池可以避免这种操作，连接池类似于线程池	（数据连接池视频的介绍）
 * 
 * 此类用于展示基础的连接池用法 
 * 
 * 
 * 使用的连接池是Druid（得奴地）
 * 
 * 用法与其他的大部分连接池没有区别
 * 其他连接池 : DBCP C3P0 Proxool
 * 
 * 其他连接池实际使用效果区别不大  Druid对mysql和oracle进行了优化（速度快）
 * */


public class DruidPoolTest {
	public static void main(String[] args) throws Exception {
		/* 以下代码 了解即可 
		 * 
		 * 实际工作不这样
		 * 
		 * */
		
		//1.创建一个Druid的数据库对象
		DruidDataSource dds = new DruidDataSource();
		
		//2.设置相应的参数driver  url   user  password（数据库的参数）
		
		//new Driver()导包   com.mysql.jdbc.Driver;
		dds.setDriver(new Driver());
		dds.setUrl("jdbc:mysql://localhost:3306/wangsql");
		dds.setUsername("root");
		//dds.setPassword("root");这里的密码为空，所以不用设置
		
		
		//3.设置连接池的基本配置（可略）----架构师设置（取决于公司服务器的压力）
		dds.setMaxActive(50);//最大活动的数据库连接多少个
		dds.setInitialSize(10);//
		dds.setMaxWaitThreadCount(5);//最多等待线程数量
		
		//4.正常使用（数据库对象获取连接）
		Connection conn = dds.getConnection();
		
		Statement stmt = conn.createStatement();
		
		
		//5.创建sql语句
		String sql = "create table wudi(username varchar(20),"
					+ "password varchar(20),"
					+ "sex char(1))";
		
		//6.执行sql
		int result = stmt.executeUpdate(sql);
		
		//显示结果
		System.out.println(result >= 0 ? "成功" : "失败");
		
		
		
	}
}
