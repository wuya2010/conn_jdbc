package mysql

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2020/7/18 8:42
  *
  */
object mysqlWrite {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("hbase").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //配置信息
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://127.0.0.1:3306/wangsql" //连接本地mysql
    val userName = "root"
    val passWd = ""

    //造数
    val rdd2 = sc.parallelize(Array("小王","小何"))

    val sql  = "insert into apple(name) values(?)" //主键不重复

    //通过批的方式插入
    //方式1：
    rdd2.foreachPartition(partition => {
      Class.forName(driver)
      val conn: Connection = DriverManager.getConnection(url, userName, passWd)
      val prepStat: PreparedStatement = conn.prepareStatement(sql)
      //todo: 通过bach的方式
      partition.foreach{x =>
        prepStat.setString(1,x)//todo: 这里1对应的是 ？ 位置
        prepStat.addBatch()
      }
      prepStat.executeBatch()
      prepStat.close()
      conn.close()
    })
    sc.stop()
  }
}
