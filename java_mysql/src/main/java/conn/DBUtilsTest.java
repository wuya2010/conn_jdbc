package conn;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Before;
import utls.JDBCUtils;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 
 * 第7步：
 * 
 * 
 * 
 * 此类代表实际工作中大部分应用情况
 * 
 * （前面6步，   都是进行与连接的关系才行， 而这里直接通过一个新的jar包实现）
 * 
 *  QueryRunner 可以实现jdbc中 增删改查的大部分功能
 * 
 *  使用两个方法实现大部分功能
 *  	update(conn,String sql,Object ... params)（可变参数）
 *  	query(conn,String sql)
 * 	
 * 
 *  当使用query方法时：
 *  	需要了解结果集处理器 ResultSetHandler 接口
 *  	实现类 : 
	 *  	BeanHandler    单个对象的处理器
	 *  	BeanListHandler 集合对象处理器
	 *  	ScalarHandler  任意对象处理器  ---> Object
 * */


public class DBUtilsTest {
	private QueryRunner queryRunner;
	private Connection conn;
	
	@Before//使用Test时都会调用before
	public void before(){
		queryRunner = new QueryRunner();
		conn = JDBCUtils.getConnection();//静态方法，JDBCUtils.getconnection()
	}
	
	/* 使用QueryRunner 实现插入数据*/
	@Test
	public void insertTest() throws SQLException {
		String sql = "insert into girl values(?,?,?,?)";
		int result = queryRunner.update(conn,sql,9,"张菲","18310720233",null);
	
		System.out.println(result > 0 ? "插入成功" : "插入失败");

	}
	
	/* 使用QueryRunner 实现删除数据 */
	@Test
	public void deleteTest() throws SQLException{
		String sql = "delete from girls where gid = ?";
		int result = queryRunner.update(conn,sql,7);
	
		System.out.println(result > 0 ? "删除成功" : "删除失败");
	
	}
	
	/* 使用QueryRunner 实现修改数据 */
	@Test
	public void updateTest() throws SQLException{
		String sql = "update girls set bid = ? where gid = ?";
		int result = queryRunner.update(conn,sql,5,6);
	
		System.out.println(result > 0 ? "修改成功" : "修改失败");
	
	}
	
	
	/* 使用QueryRunner 实现查询单个对象数据 */
	@Test
	public void selectSampleTest() throws SQLException{
		String sql = "select * from girls where gid = ?";
		Girl girl =  queryRunner.query(
				conn,sql,new BeanHandler<Girl>(Girl.class),1);
	
		System.out.println(girl);
	
	}
	
	/* 使用QueryRunner 实现查询多个对象数据 */
	@Test
	public void selectListTest() throws SQLException{
		String sql = "select * from girls limit 0,5";
		
		List<Girl> girlList = queryRunner.query(
				conn,sql,new BeanListHandler<Girl>(Girl.class));
		
		for (Girl girl : girlList) {
			System.out.println(girl);
		}
	
	}
	
	/* 使用QueryRunner 实现任意对象数据  */
	@Test
	public void selectObjectTest() throws SQLException{
		String sql = "select count(*) from girls";
		
		Object obj = queryRunner.query(
				conn,sql,new ScalarHandler());
		
		System.out.println(obj);
	
	}
	
}
