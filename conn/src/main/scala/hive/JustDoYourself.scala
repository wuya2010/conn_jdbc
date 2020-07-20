package com.atguigu

import org.apache.spark.sql.SparkSession


object JustDoYourself {

  def main(args: Array[String]): Unit = {

    System.setProperty("HADOOP_USER_NAME", "atguigu")

    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("Hive")
      .enableHiveSupport()  // 支持hive
      .config("spark.sql.warehouse.dir", "hdfs://hadoop102:9000/user/hive/warehouse")
      .getOrCreate()

    import spark.implicits._



    val df  = spark.read.json("hdfs://hadoop102:9000/user/atguigu/ods/baseadlog.log")

    //  spark.sql("show tables").show
      val ds = df.as[base]

    spark.sql("use dwd")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    ds.createOrReplaceTempView("basead")

   // ds.collect().foreach(data=>println(data))

    spark.sql("select  * from basead").show

    spark.sql("insert into table dwd_base_ad  select * from basead")



  }
}

case class base(adid :String, adname:String, dn:String)