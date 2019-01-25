package operator.transformation

import org.apache.spark.{SparkConf, SparkContext}

object coalesce {
    def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setMaster("local[5]").setAppName("app")
      val sc = new SparkContext(conf)

      val datas = List("hi", "hello", "how", "are", "you")
      val datasRDD = sc.parallelize(datas, 7) //7代表默认是7个分区
      println("RDD的分区数: " + datasRDD.partitions.length)

      //用于将RDD进行重分区，使用HashPartitioner。
      // 且该RDD的分区个数等于numPartitions个数。
      // 如果shuffle设置为true，则会进行shuffle。
      val datasRDD2 = datasRDD.coalesce(2, true)
      println("RDD的分区数: " + datasRDD2.partitions.length)
      val datasRDD3 = datasRDD.coalesce(2, false)
      println("RDD的分区数: " + datasRDD3.partitions.length)
      val datasRDD4 = datasRDD.coalesce(9, true)
      println("RDD的分区数: " + datasRDD4.partitions.length)
      val datasRDD5 = datasRDD.coalesce(9, false)
      println("RDD的分区数: " + datasRDD5.partitions.length)

    }
}
