package connpool;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author kylinWang
 * @data 2019/12/27 23:03
 */
public class PrepareStatementTest {
    //空参构造器
    public PrepareStatementTest(){
    }

    @Test
    public void insertTest(){
        //调用方法，获取连接
        Connection connect = JDBCUtils.getConnect();
        //直接定义
        PreparedStatement preparedStatement = null;
        //  Statement statement = connect.createStatement();
        //  Properties properties = System.getProperties();

        Scanner scanner = new Scanner(System.in);
        System.out.println("年纪：");
        int age = scanner.nextInt();
        System.out.println("姓名：");
        String name = scanner.nextLine();
        String sql = "insert into stu values(?,?)";


        try {
            //具体执行;
            preparedStatement = connect.prepareStatement(sql);
            //传入2个参数：  int parameterIndex, Object x
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,age);
            preparedStatement.setObject(1,20);
            preparedStatement.setObject(2,"小米");
            int result = preparedStatement.executeUpdate();
            boolean is_OK = preparedStatement.execute();
            System.out.println(is_OK);
            System.out.println((result > 0 ? "成功" : "失败"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
