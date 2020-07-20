package kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author kylinWang
  * @data 2020/7/19 18:08
  *
  */
object getKafkaDs {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("DauApp").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(3))

    val sourceDStream: InputDStream[(String, String)] = kafkaUtil.getKafkaStream(ssc, "topic")
    //打印输出
    println(sourceDStream)

  }
}
