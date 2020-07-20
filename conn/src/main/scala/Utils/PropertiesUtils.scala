package Utils

import java.io.InputStream
import java.util.Properties

/**
  * @author kylinWang
  * @data 2020/7/18 9:22
  *
  */
object PropertiesUtils {

  //怎么获取配置，怎么样的形式
  import scala.collection.mutable
  val propMap = mutable.Map.empty[String, Properties]

  //todo ： 这里加“/”, 这个每次获取一个参数都要重新加载，所以用一个map---> 方法： getOrElseUpdata
  def getPropterty(path: String, propName: String)={
    //如果已经存在，直接获取； 如果没有加入map
    val prop = propMap.getOrElseUpdate(path,{
      val properties = new Properties()
      val fis: InputStream = this.getClass.getResourceAsStream("/"+ path)
      properties.load(fis)
      properties //返回值
    })

    prop.getProperty(propName)
  }

  def main(args: Array[String]): Unit = {
    val t = PropertiesUtils.getPropterty("config.properties","redis.host")
    println(t)
  }
}
