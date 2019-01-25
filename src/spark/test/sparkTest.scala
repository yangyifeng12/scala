package spark.test

import org.apache.spark.{SparkConf,SparkContext}
object sparkTest {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local[3]").setAppName("app")
    val sc=new SparkContext(conf)

    val input=sc.textFile("D://a.txt")
    val result=input.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    result.foreach(println)
  }
}
