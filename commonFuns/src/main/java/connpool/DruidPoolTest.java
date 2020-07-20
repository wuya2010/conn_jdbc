package connpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;


/**
 * @author kylinWang
 * @data 2019/12/27 0:18
 */
public class DruidPoolTest {
    //空参构造器
    public DruidPoolTest(){

    }

    //获取配置文件信息
    @Test
    public  void druidTest() throws IOException {
        //properties:  Creates an empty property list with no default values.
        Properties prop = new Properties();

        try {

            FileInputStream is = new FileInputStream(new File("E:\\01_myselfProject\\TestJDBC\\src\\main\\resources\\druid.properties"));
            //load: 从输入中读取属性列表(键和元素对)字节流。
            prop.load(is);
            //get: Returns the value to which the specified key is mapped
            Object username = prop.get("username");
            System.out.println(username);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getConfig() throws Exception {

        Properties props = new Properties();

        ClassLoader classLoader = this.getClass().getClassLoader();

        //getClass获得的是子类？
        Class<? extends DruidPoolTest> aClass = this.getClass();

        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("druid.properties"));
          /*  Object username = props.get("username");
            System.out.println(username);*/
            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
            //Attempts to establish a connection with the data source
            Connection connect = dataSource.getConnection();
            //Creates a <code>Statement</code> object for sending SQL statements to the database.

            //创建一个<code>语句</code>对象，用于向数据库发送SQL语句。
            Statement stmt = connect.createStatement();

            String sql = "insert into admin values('admin','admin','m')";
            //执行sql语句
            int result = stmt.executeUpdate(sql);

            //关闭连接
            stmt.close();
            connect.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void getConfig2(){


    }


}
