package kafka

import Utils.PropertiesUtils
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka.KafkaUtils





/**
  * @author kylinWang
  * @data 2020/7/18 9:44
  *
  */
object kafkaUtil {

    def getKafkaStream(ssc:StreamingContext, topic:String)={

      val params: Map[String, String] = Map(
          //通过配置获取
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> PropertiesUtils.getPropterty("config.properties", "kafka.broker.list"),
          ConsumerConfig.GROUP_ID_CONFIG -> PropertiesUtils.getPropterty("config.properties", "kafka.group") )

      //todo: 需要指定参数 , 指定了返回值类型： String, string
      // [String, String, StringDecoder, StringDecoder]-->[k, v , ks, vs]
      KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
        ssc,
        params,
        Set(topic)
      )


    }
}
