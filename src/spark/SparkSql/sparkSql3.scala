package spark.SparkSql

import java.util.Properties

import org.apache.spark.sql.SparkSession

object sparkSql3 {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[3]").appName("ScalaTestOld").getOrCreate()
    import spark.implicits._
    /**
      * 两种spark读取jdbc的方法
      */
      //1
    //    spark.read.format("jdbc")
//      .option("url", "jdbc:mysql://localhost:3306/scala")
//      .option("dbtable","user")
//      .option("user","root")
//      .option("password","root")
//      .load().show()
  //2
    val props = new Properties()
    props.put("user","root")
    props.put("password","root")

   val userDS = spark.read.jdbc("jdbc:mysql://localhost:3306/scala","user",props)
    //把userDS读出来的表存储为user1（转储数据库表）
//    userDS.write.jdbc("jdbc:mysql://localhost:3306/scala","user1",props)
    //把userDS读出来的表存储为持久表users
//    userDS.createOrReplaceTempView("users")
    //按条件查询出的结果存储为user3
    /*spark.sql("select userId,userName from users")
          .write.jdbc("jdbc:mysql://localhost:3306/scala","user3",props)*/
  }
}
