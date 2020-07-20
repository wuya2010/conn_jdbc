package hive

import org.apache.spark.sql.SparkSession


object JustDoYourself3 {

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



    val df  = spark.read.json("hdfs://hadoop102:9000/user/atguigu/ods/member.log")


    //  spark.sql("show tables").show
    val dss = df.as[member]

    //pat=/(\d{3})\d*(\d{4})/    b=str.replace(pat,'$1****$2');
    val ds = dss.map {
      case line => (line.ad_id.toInt,
        line.birthday, line.dn,
        line.dt, line.email, line.fullname, line.iconurl,
        line.lastlogin, line.mailaddr, line.memberlevel,
        line.password.replaceAll("\\d+", "******"),
        line.paymoney,
        line.phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"),
        line.qq, line.register, line.regupdatetime,
        line.uid.toInt,
        line.unitname, line.userip, line.zipcode)
    }.toDF("ad_id","birthday","dn","dt","email","fullname",
    "iconurl","lastlogin","mailaddr","memberlevel","password","paymoney","phone",
    "qq","register","regupdatetime","uid","unitname","userip","zipcode")

  //  val ds = df2.as[member]


    spark.sql("use dwd")
    spark.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    ds.createOrReplaceTempView("membertable")



   // ds.collect().foreach(data=>println(data))

    spark.sql("select   uid  , ad_id   , birthday  , email , fullname   ," +
      "iconurl  ,lastlogin  ,mailaddr  , memberlevel  ,password  ,paymoney  ,phone  ," +
      "qq ,register  ,regupdatetime  ,unitname ,userip ,zipcode from membertable limit 10").show

   // spark.sql("select * from membertable").show

   spark.sql("insert overwrite  table dwd_member partition(dt = '2019-10-30',dn ='webA') " +
     "select  uid  , ad_id   , birthday  , email , fullname   ,iconurl  , lastlogin  ,mailaddr  , memberlevel  ,password  ,paymoney  ,phone  ,qq  " +
     "register  ,regupdatetime  ,unitname ,userip ,zipcode  ,dt from   membertable")

  }
}

case class member(ad_id  :String, birthday :String, dn :String,
                        dt :String,email :String,fullname:String,iconurl :String,
                        lastlogin:String,mailaddr:String,memberlevel:String,password:String,
                        paymoney:String,phone:String,qq:String,register:String,regupdatetime:String,
                        uid:String,unitname:String,userip:String,zipcode:String)

