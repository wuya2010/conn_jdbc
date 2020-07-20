package utls;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author kylinWang
 * @data 2020/7/18 10:02
 */
public class JDBCUtils {
    //声明一个静态私有属性
    private static DataSource ds;


    //只需要执行一次
    static{
        try {
            //1.创建一个Properties对象
            Properties prop = new Properties();
            //2.加载配置文件
            prop.load(
                    JDBCUtils.class.getClassLoader().
                            getResourceAsStream("druid.properties"));
            //3.根据配置文件创建datasource
            ds = DruidDataSourceFactory.
                    createDataSource(prop);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    //获取连接的方法------->静态方法
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    //关流的方法 需要关闭 Statement Connection 对象
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
