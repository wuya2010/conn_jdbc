package redis

import java.text.SimpleDateFormat
import java.util
import java.util.Date

import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

/**
  * @author kylinWang
  * @data 2020/7/18 9:43
  *
  */
object redis_test {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("DauApp").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(3))

    val client: Jedis = RedisUtil.getJedisPool
    // smembers:  Return all the members (elements) of the set value stored at key
    val uidSet: util.Set[String] = client.smembers("Wang"+ ":" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
   // redis 广播变量
    val uidSetBC: Broadcast[util.Set[String]] = ssc.sparkContext.broadcast(uidSet)

    //取 redis 值
    val redis_value = uidSetBC.value

    client.close()
  }
}
