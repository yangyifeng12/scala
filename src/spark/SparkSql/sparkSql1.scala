package spark.SparkSql


import org.apache.spark.sql.{SparkSession,Row}
import org.apache.spark.sql.types._
case class person(name:String,age:Long)
object sparkSql1 {
//如果不指定表头schemal，只有json能够有，其他都是空
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("ScalaTestOld").master("local[3]")
      .getOrCreate()
    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._

    val peopleDs = spark.sparkContext.textFile("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/people.txt")
//       .map(_.split(","))
//      .map(attr => person(attr(0).trim,attr(1).trim.toLong))
        .toDS()
//    peopleDs.show()
//    peopleDs.createOrReplaceTempView("people")
//    val peopleView = spark.sql("select name,age from people").show()

      //自定义指定表头（schema）
    val peopleRdd = spark.sparkContext.textFile("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/people.txt")
    //模式用字符串编码
    val schemalString = "name age"
    //根据模式
    val fields = schemalString.split(" ")
          .map { fieldNames => {
            if (fieldNames == "age") {
              StructField(fieldNames, IntegerType, true)
            } else {
              StructField(fieldNames, StringType, true)
            }
          }
          }
    val schema = StructType(fields)
    //将RDD（人员）的记录转换为行
    val rowRDD = peopleRdd
      .map(_.split(","))
      .map(attributes =>Row(attributes(0),attributes(1).trim.toInt))
    //将模式应用于RDD
    val peopleDF = spark.createDataFrame(rowRDD,schema)
    //使用DataFrame
    peopleDF.show()
    // SQL可以在使用DataFrames

    // SQL查询的结果是DataFrames并支持所有正常的RDD操作
    //结果中行的列可以通过字段索引或字段名称





    //结果中一行的列可以通过字段索引
//    peopleView.map(tee =>"name:"+tee(0)).show()
    //或通过字段名称
//    peopleView.map(tee=> "name:" + tee.getAs[String]("name")).show()

   /* //没有为数据集[Map [K，V]]预定义的编码器，明确定义
    implicit val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]
    //原始类型和case类也可以定义为
    //隐式val stringIntMapEncoder：编码器[Map [String，Any]] = ExpressionEncoder（）
    // row.getValuesMap [T]一次检索多个列到一个Map [String，T]
     val a=peopleView.map(tee => tee.getValuesMap[Any](List("name","age"))).collect()*/

    /*//DataFrame
    val df1= spark.read.json("D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/people.json")
//    df1.printSchema()//输出类名
    root
    |-- age: long (nullable = true)
    |-- name: string (nullable = true)
    val ds=df1.select("name").show()
    val ds2=df1.select($"name",$"age"+1).show()
    //将DataFrame注册为SQL临时视图 只能在当前session能够使用
    df1.createOrReplaceTempView("people")
    val peopleDS=spark.sql("select * from people where name='Andy'").show()
    //将DataFrame注册为全局临时视图 不同的session也能使用
    df1.createGlobalTempView("peao")
    val p=spark.sql("select * from global_temp.peao ").show()

    val personDS = Seq(person("Andy",32),person("aa",32)).toDS()
    val personRdd = personDS.map( _+"s").collect()//Array[String]已经变成数据了*/
    //通过提供一个类可以将DataFrames转换为数据集。映射将通过名称（dataFrame和person类做匹配）
    /*val path = "D://softwares//spark-2.1.1-bin-hadoop2.7//examples//src//main//resources/people.json"
    val peopleDs = spark.read.json(path).as[person]
    peopleDs.show()*/








  }

}
