package operator.transformation

import org.apache.spark.{SparkConf, SparkContext}

object cogroup {
    def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setMaster("local[3]").setAppName("app")
      val sc = new SparkContext(conf)

      val datas1 = List((1,"苹果"),(2, "梨"),(3, "香蕉"),(4, "石榴"))

      val datas2 = List((1, 7),(2, 3),(3, 8),(4, 3))

      val datas3 = List((1, "7"),(2, "3"),(3, "8"),(4, "3"),(4, "4"),(4, "5"),(4, "6"),(5,"8"))

      val rdd1 = sc.parallelize(datas1)
      val rdd2 = sc.parallelize(datas2)
      val rdd3 = sc.parallelize(datas3)

      //cogroup:对******多个********RDD中的KV元素，每个RDD中相同key中的元素分别聚合成一个集合。
      //与reduceByKey不同的是针对两个RDD中相同的key的元素进行合并。
//      rdd1.cogroup(rdd2,rdd3).foreach(println)
      rdd2.cogroup(rdd3).foreach(println)
      /*(5,(CompactBuffer(),CompactBuffer(),CompactBuffer(8)))
      (3,(CompactBuffer(香蕉),CompactBuffer(8),CompactBuffer(8)))
      (2,(CompactBuffer(梨),CompactBuffer(3),CompactBuffer(3)))
      (4,(CompactBuffer(石榴),CompactBuffer(3),CompactBuffer(3, 4, 5, 6)))
      (1,(CompactBuffer(苹果),CompactBuffer(7),CompactBuffer(7)))*/
  }
}
