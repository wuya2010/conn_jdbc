package mysql

import java.sql.{Connection, DriverManager, ResultSet}

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2020/7/18 8:41
  *
  */
object mysqlRead {

  def main(args: Array[String]): Unit = {

    //build spark
    val conf = new SparkConf().setAppName("hbase").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //配置信息
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://127.0.0.1:3306/wangsql" //连接本地mysql
    val userName = "root"
    val passWd = ""

    //建立连接
    import org.apache.spark.rdd.JdbcRDD
    //todo: 实现： 对JDBC连接执行SQL查询并读取结果的RDD，传参：
//    (
//      sc: SparkContext,
//      getConnection: () => Connection,
//      sql: String,
//      lowerBound: Long, (the minimum value of the first placeholder)
//      upperBound: Long,
//      numPartitions: Int,
//      mapRow: (ResultSet) => T = JdbcRDD.resultSetToObjectArray _)
    val ret_rdd = new JdbcRDD(sc,
      ()=>{
       Class.forName(driver) //todo: 返回 值 Class[]
        DriverManager.getConnection(url, userName, passWd)
      },
      "select * from t_dept  where ? <= id and id <= ? ",
  1,
  5, //定义？ 的上下限
  2,
      result => result.getString(2) //todo: 根据类型输出第 n 行
    )

    ret_rdd.collect().foreach(println)

    sc.stop()
  }

}
