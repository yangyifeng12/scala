package StudentTest.Clean

import java.io.{File, FileWriter, StringReader}

import au.com.bytecode.opencsv.CSVReader
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source
object StudentCleanBase {
  /**
    * * netClean.csv文件,一个过滤空值的方法
    *csv文件文件用,每行的数据用,分隔的
    * netClean.csv文件表结构是学号-开始上网的时间-结束上网的时间
    * @param s 代表通过textFile从文件读取出的每行数据
    * @return  返回
    */

  def filterData(s:String):Boolean ={//返回值为true的留下，false的去掉
    val para=s.split(",")
    val startTime =para(1)
    val endTime=para(2)
    var bol:Boolean=true
    if(para==null||para==None){
      bol=false
    }
    bol
  }

  def main(args: Array[String]): Unit = {
    val conf= new SparkConf().setMaster("local[3" +
      "]").setAppName("app")
    val sc=new SparkContext(conf)

//    val sexList=sc.textFile("D://通用共享资源/大数据/spark/03spark练习/3.学生行为分析-上网/sexDictFile.csv").flatMap(_.)
    //把所有学号和性别的表输出出来 2015011203,2
    val sexList=Source.fromFile("D://通用共享资源/大数据/spark/03spark练习/3.学生行为分析-上网/sexDictFile.csv").getLines().toList
//    sexList.foreach(println)
    //把Map.toBuffer,通过toBuffer方法把变成可变集合
  val sexMapBuf=Map[String,String]().toBuffer
    //把学号和性别拆分开来放到Map中方便根据Key的值查询性别
    for(i <- sexList){
      val p=i.split(",").toList
      sexMapBuf += (p(0).trim ->p(1).trim)
    }
    val sexMap=sexMapBuf.toMap
    val netList=sc.textFile("D://通用共享资源/大数据/spark/03spark练习/3.学生行为分析-上网/netClean.csv").filter(filterData).map{
      line =>{
        val res = new CSVReader(new StringReader(line)).readNext()//把另一个rdd中的数据拿出来
          res(0)+","+sexMap(res(0))+","+res(1)+","+res(2)+"\n"
      }
    }.collect()
    netList.foreach(println)

    for(i <-netList){
      val file=new File("D://softwares/test.csv")
      val fw =new FileWriter(file,true)//用一个布尔值来构造一个FileWriter对象，它指示是否附加所写的数据
      fw.write(i)
      fw.close()
    }

  }
}
