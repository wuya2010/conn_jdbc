package scala.hbase

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.{TableInputFormat, TableOutputFormat}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author kylinWang
  * @data 2020/7/18 8:15
  *
  */
object hbaseWrite {

  def main(args: Array[String]): Unit = {

    //build spark
    val conf = new SparkConf().setAppName("hbase").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //connect: Creates a Configuration with HBase resources
    val HbaseConf: Configuration = HBaseConfiguration.create()
    //hbase cluster
    HbaseConf.set("hbase.zookeeper.quorum", "hadoop105,hadoop106,hadoop107")
    HbaseConf.set(TableInputFormat.INPUT_TABLE,"student")

    //设置实例
    val job = Job.getInstance(HbaseConf)
    job.setOutputFormatClass(classOf[TableOutputFormat[ImmutableBytesWritable]])
    job.setOutputKeyClass(classOf[ImmutableBytesWritable])
    job.setOutputValueClass(classOf[Put])

    //写入
    val initialRDD = sc.parallelize(List(("10", "apple", "11"), ("20", "banana", "12"), ("30", "pear", "13")))
    val hbasaRDD  = initialRDD.map{
      case (row, name, weight) => {
        val rowkey = new ImmutableBytesWritable()
        rowkey.set(Bytes.toBytes(row))

        val put = new Put(Bytes.toBytes(row))
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(name))
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("weight"), Bytes.toBytes(weight))
        (rowkey, put)

      }
    }
    hbasaRDD.saveAsNewAPIHadoopDataset(job.getConfiguration)
    sc.stop()

  }

}
