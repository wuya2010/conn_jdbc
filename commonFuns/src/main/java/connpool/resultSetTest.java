package connpool;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kylinWang
 * @data 2019/12/28 7:29
 */
public class resultSetTest {

    @Test
    public void connectTest(){
        Connection connect = JDBCUtils.getConnect();
        PreparedStatement preparedStatement = null;

        String sql = "select * from stu";
        //定义结果集: 表示数据库结果集的数据表, 通常由执行查询数据库的语句生成。
        //答应输出执行的结果
        ResultSet result = null;

        try {
            //执行sql
            preparedStatement = connect.prepareStatement(sql);
            //执行成功或失败
           //int update = preparedStatement.executeUpdate();
            result = preparedStatement.executeQuery();
            while (result.next()){
                //根据标签得到结果
                String name = result.getString("name");
                int age = result.getInt("id");
                System.out.println(age + " "+ name);
            }

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
