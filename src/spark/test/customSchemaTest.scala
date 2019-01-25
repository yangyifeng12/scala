package spark.test

import org.apache.spark.sql.{SparkSession,Row}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object customSchemaTest {


  def main(args: Array[String]): Unit = {
    //创建SqlSession
    val spark=SparkSession.builder().master("local[3]").appName("ScalaTestOld").getOrCreate()
    import spark.implicits._
    //1,自定义schemal
    val schemalString = "name age"
    //2 切割 schema成跟数据相同的格式
    val sc =schemalString.split(" ")
              .map{ aa =>{
                  if(aa=="name"){
                    StructField(aa,StringType,true)
                  }else{
                    StructField(aa,IntegerType,true)
                  }
                }
              }
    val schema = StructType(sc)
    //把数据读成RDD的格式
    val dataRdd = spark.sparkContext.textFile("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/people.txt")
    //把Rdd切分成行
    val rowRDD = dataRdd.map(_.split(",")).map(att => Row(att(0),att(1).trim.toInt))
    //将模式应用于RDD
    val peopleDs = spark.createDataFrame(rowRDD,schema)


  }
}
