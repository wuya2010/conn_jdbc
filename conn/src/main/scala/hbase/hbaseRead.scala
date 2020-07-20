package scala.hbase

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{Cell, CellUtil, HBaseConfiguration}
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2020/7/18 8:15
  *
  */
object hbaseRead {

  def main(args: Array[String]): Unit = {

        //build spark
        val conf = new SparkConf().setAppName("hbase").setMaster("local[*]")
        val sc = new SparkContext(conf)

        //connect: Creates a Configuration with HBase resources
        val HbaseConf: Configuration = HBaseConfiguration.create()
        //hbase cluster
        HbaseConf.set("hbase.zookeeper.quorum", "hadoop105,hadoop106,hadoop107")
        HbaseConf.set(TableInputFormat.INPUT_TABLE,"student")

        //todo: 理解
        val rdd1 = sc.newAPIHadoopRDD(
          HbaseConf,
          classOf[TableInputFormat],
          classOf[ImmutableBytesWritable],
          classOf[Result]
        )

        val rdd2 = rdd1.map{
          case (key ,value) => {
            //实现1： 留意import的包
            // Bytes.toString(result.getRow)
            // Bytes.toString(key.get())

            //实现2
           val cells = value.listCells()
            // 带入隐式转换, 内置了很多java和scala集合互转的方法
            import scala.collection.JavaConversions._
            for(cell <- cells){
              println(Bytes.toString(CellUtil.cloneQualifier(cell)))
            }
          }
        }
  }

}
