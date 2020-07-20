package connpool;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

/**
 * @author kylinWang
 * @data 2019/12/28 7:53
 */


//QueryRunner 可以实现jdbc中 增删改查的大部分功能
    /*
     *  使用两个方法实现大部分功能
     *  	update(conn,String sql,Object ... params)（可变参数）
     *  	query(conn,String sql)

     */
public class queryRunnerTest {

    private Connection connect;
    private QueryRunner queryRunner;

    @Before
    public void before(){
        //不执行这句，无法获取连接
        queryRunner = new QueryRunner();
        //获取连接
        this.connect = JDBCUtils.getConnect();
    }


    //update: 可是实现 insert , update ,  delete
    @Test
    public void insertTest() throws SQLException {
        String sql = "insert into stu values(?,?)";//id 1,name '天明'
        int result = queryRunner.update(connect, sql, 100, "天云");
        System.out.println(result > 0 ? "牛逼了":"不行");
    }

    @Test
    public void updateTest() throws SQLException {
        String sql = "update stu set name = ? where name = '天云'";//id 1,name '天明'
        int result = queryRunner.update(connect, sql,  "天云2");
        System.out.println(result > 0 ? "天云2":"天云");
    }


    //查询服务
    @Test
    public void queryTest(){
        String sql = "select * from stu";
        //queryRunner.query(connect,sql,new BeanHandler<>())
       // queryRunner.query()


    }



}
