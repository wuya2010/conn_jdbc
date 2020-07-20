package redis

import Utils.PropertiesUtils
import redis.clients.jedis.{JedisPool, JedisPoolConfig}

/**
  * @author kylinWang
  * @data 2020/7/18 9:18
  *
  */
object RedisUtil {

  val host = PropertiesUtils.getPropterty("config.properties", "redis.host")
  val port = PropertiesUtils.getPropterty("config.properties","redis.port").toInt

   //定义属性
  private val jedisConf = new JedisPoolConfig()
  jedisConf.setMaxTotal(100) //最大连接数
  jedisConf.setMaxIdle(20) //最大空闲
  jedisConf.setMinIdle(20) //最小空闲
  jedisConf.setBlockWhenExhausted(true) //忙碌时是否等待
  jedisConf.setMaxWaitMillis(500) //忙碌时等待时长 毫秒
  jedisConf.setTestOnBorrow(false) //每次获得连接的进行测试

  //todo: jedis 连接池
  private val jedisPool = new JedisPool(jedisConf,host,port)

  def getJedisPool = {
    jedisPool.getResource
  }

}
