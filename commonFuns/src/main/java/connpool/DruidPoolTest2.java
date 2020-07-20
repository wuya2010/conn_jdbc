package connpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.hadoop.mapred.FileInputFormat;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author kylinWang
 * @data 2020/6/3 18:54
 */
public class DruidPoolTest2 {

    public DruidPoolTest2(){ }
    @Test
    public  void druidTest() throws IOException {

    //读取配置文件
    FileInputStream fis = null;
    Properties properties = new Properties();
    {
        try {
            fis = new FileInputStream(new File("E:\\01_myselfProject\\TestJDBC\\src\\main\\resources\\druid.properties"));
            properties.load(fis);
            String username = properties.getProperty("username");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        }
    }


    @Test
    public void getDruidPool(){
        Properties properties = new Properties();
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream("E:\\01_myselfProject\\TestJDBC\\src\\main\\resources\\druid.properties");

        try {
            properties.load(fis);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            String sql = "insert into admin values('admin','admin','m')";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Statement statement = connection.createStatement();
            int ret = statement.executeUpdate(sql);
            System.out.println(ret);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
