package hive

import org.apache.spark.sql.SparkSession


object JustDoYourself2 {

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



    val df  = spark.read.json("hdfs://hadoop102:9000/user/atguigu/ods/baswewebsite.log")


    //  spark.sql("show tables").show
    val ds = df.as[baswewebsite]

    spark.sql("use dwd")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    ds.createOrReplaceTempView("basweweb")

   // ds.collect().foreach(data=>println(data))

    spark.sql("select  * from basweweb").show

    spark.sql("insert overwrite  table dwd_base_website partition(dn='2019-10-30')  " +
      "select  siteid, sitename, siteurl, `delete`, createtime ,creator from   basweweb")



  }
}

case class baswewebsite(createtime  :String, creator :String, delete :String,
                        dn :String,siteid :String,sitename:String,siteurl :String)

