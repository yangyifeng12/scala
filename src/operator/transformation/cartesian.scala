package operator.transformation

import org.apache.spark.{SparkConf, SparkContext}

object cartesian {
    def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setMaster("local[1]").setAppName("app")//[1]代表一个线程
      val sc = new SparkContext(conf)

      val list1 = List(1,2,3,4,5)
      val list2 = List(60,70,80,90,100,20)

      /**
        * 创建算子 把数据变成rdd   本次是把List变换成rdd 使用parallelize
        *           还有textFile 不常用的wholeTextFile
        */
      val rdd1 = sc.parallelize(list1)
      val rdd2 = sc.parallelize(list2)

      //两个RDD进行笛卡尔积合并
      //第一种方法
//      println(rdd1.cartesian(rdd2).collect.toList)
      //第二种方法
      rdd1.cartesian(rdd2).foreach(println)
//      (1,60)
//      (1,70)
//      (1,80)
//      (1,90)
//      (1,100)
//      (1,20)
//      (2,60)
//      (2,70)
//      (2,80)
//      (2,90)
//      (2,100)
//      (2,20)
//      (3,60)
//      (3,70)
//      (3,80)
//      (3,90)
//      (3,100)
//      (3,20)
//      (4,60)
//      (4,70)
//      (4,80)
//      (4,90)
//      (4,100)
//      (4,20)
//      (5,60)
//      (5,70)
//      (5,80)
//      (5,90)
//      (5,100)
//      (5,20)
  }
}
