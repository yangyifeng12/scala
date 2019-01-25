package spark.SparkSql

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkSql2 {
  def main(args: Array[String]): Unit = {
    //创建sqlSession
    val spark = SparkSession.builder().master("local[3]").appName("ScalaTestOld").getOrCreate()
    import spark.implicits._

  //读文件
    //不指定文件的类型
    val userDs = spark.read.load("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/users.parquet")
    //指定读文件的类型，读文件的类型必须跟要读取的文件类型一直，要不会乱码
//    val userDs1 = spark.read.format("parquet").load("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/users.parquet")
  //写文件不指定类型是.parquet的文件
    userDs.write.save("D://softwares//test")
    //指定存储的类型
//    userDs1.write.format("json").save("D://softwares//test1")
    //指定表里面是否有表头
//    val userDS = spark.read.format("csv")
//                .option("header","true")//把第一行当成表头schema
//                 .load("D://softwares/test.csv")
    //show里面能写数字代表显示多少行，默认20行

    //用sql语句直接读取文件 select * from 文件类型.`文件的路径`************注意是飘符号，esc符号下面的***********
//    val userSql = spark.sql("select * from csv.`D://softwares/test1.csv`")

    //保存文件夹的类型**********save（）里面是文件夹的路径****************下面这些也是控制文件夹怎么放的方式
       //SaveMode.ErrorIfExists （默认）	"error" or "errorifexists" （默认）	将DataFrame保存到数据源时，如果数据已经存在，则预计会抛出异常。
       // SaveMode.Append	"append"	将DataFrame保存到数据源时，如果数据/表已存在，则DataFrame的内容预计会附加到现有数据。
        //SaveMode.Overwrite	"overwrite"	覆盖模式意味着将DataFrame保存到数据源时，如果数据/表已存在，则预期现有数据将被DataFrame的内容覆盖。
    //SaveMode.Ignore	"ignore"	忽略模式意味着将DataFrame保存到数据源时，如果数据已经存在，则保存操作不会保存DataFrame的内容，也不会更改现有数据。这与CREATE TABLE IF NOT EXISTSSQL中的类似。

    //     spark.sql("select * from csv.`D://softwares/test.csv`")
//       .write.mode(SaveMode.Append).format("csv").save("D://softwares//test1")
    //保存到持久表
//    val s = spark.sql("select * from csv.`D://softwares/test.csv`")
//            .write.mode(SaveMode.Append)
//              .option("path","D://softwares//a")
//                .format("json")
//                  .saveAsTable("kk")//保存到持久表
//    val df=spark.sql("select * from kk").show()
//**********分区和分桶不能是相同的一列************分区之后生成的文件没有分区所用的那列(分区之后生成多个子文件，存放各个分区的文件)
// 分桶是把文件夹里面的文件分桶，
    val Ds =spark.sql("select * from csv.`D://softwares/test.csv`")
    val Dss =  Ds.write
      .partitionBy("_c0")
//      .bucketBy(5,"_c2").sortBy("_c0")//根据哪列分桶
      //分桶时候要是分桶那列数据的类型不够分桶的个数，还会报错
      //一列里面就有1，2这两种数据，你分成5桶会报错
      .format("json")
      .mode(SaveMode.Append)
      .option("path","D://softwares/l")
      .option("header","true")
      .saveAsTable("net")

    //************这个持久表里面并没有改变表中的数据格式.show还是能显示出所有的列，转存文件里面改变表格式********












  }
}
