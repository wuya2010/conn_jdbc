package connpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author kylinWang
 * @data 2019/12/27 23:07
 */
public class JDBCUtils {
    private static DataSource ds;


    //加载一个datasource
    static{
        Properties properties = new Properties();

        try {
            properties.load(
            JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties")
                    // FileInputStream(new File("druid.properties"))
            );
            //创建一个datasource
           ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //封装一个方法
    public static Connection getConnect(){

        Connection conn = null;
        try {
            conn= ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn; //会提示没有初始化
    }


}



/*
    private static DataSource ds ;

//读取配置文件
static{
        Properties prop = new Properties();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.propertie");

        try {
        //获取配置文件
        prop.load(is);
        //prop.load(JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
        ds= DruidDataSourceFactory.createDataSource(prop);

        } catch (IOException e) {
        e.printStackTrace();
        } catch(Exception e2){
        e2.printStackTrace();
        }
        }


        */

