package StudentTest.Clean
import java.io.{File, FileWriter, StringReader}
import java.text.SimpleDateFormat

import au.com.bytecode.opencsv.CSVReader
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.mutable


object SurfConditionAnalyze {

  //声明文件的字段标题
  val hourOnlineCount = {
    for(i <- 0 to 24) yield{
      i+"hourOnlineCount,"
    }
  }

  def transformDataList(line:String) ={
    val reader=new CSVReader(new StringReader(line))//通过CSVReader读取信息
    val rn=reader.readNext()
    //学生Id
    val userId=rn(0)
    //添加24H的默认值

    val hourOnlineCounts = {
      val buf = mutable.Buffer[Int]()
      for(i <- 1 to 24){
        buf.append(0)
      }
      buf
    }

    //把时间格式化成能用到的类型
    val sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm")
    //开始上网的时间
    val beginTime = sdf.parse(rn(2))
    //结束上网的时间
    val endTime = sdf.parse(rn(3))

    //统计每条上网记录的中的上网小时数
    if(beginTime.getDate == endTime.getDate){
      //上网时间为同一天
      for(hours <- beginTime.getHours to endTime.getHours ){
        hourOnlineCounts(hours) +=1
      }
    }else{
      val days =(endTime.getDate-beginTime.getDate)
      for(hour <- 0 to 23){
        hourOnlineCounts(hour) += days-1
      }

      //上网时间不在同一天
      for(hour <- beginTime.getHours to 23){
        hourOnlineCounts(hour) += 1
      }
      for(hour <- 0 to endTime.getHours){
        hourOnlineCounts(hour) += 1
      }
    }

    //把开始时间转换成字符串格式，赋予变量 在线的年月
    val onlineYearMonth = new SimpleDateFormat("yyyy/MM").format(beginTime)+"/01"

    /*通过入学年份，上网时间的年，月计算学期
    （上网年份-入学年份）*2
    如果月份>6 学期再加1*/
    //学生入学年份
    val year = userId.take(4).toInt
    //上网的年份
    val onlineYear = onlineYearMonth.take(4).toInt
    //上网的月份
    val onlineMonth = onlineYearMonth.slice(5,7).toInt

    //通过年份和月份计算出学期
    var semester = (onlineYear - year) * 2
    if(onlineMonth > 6){
      semester += 1
    }
    //返回键值对
    ((userId+"#"+onlineYearMonth+"#"+semester) -> hourOnlineCounts.toList )
  }
  //把键值对的数据分解成需要写入文件的格式
  def transformUserNetHourList(s:(String,List[Int])) ={
    val userInfo = s._1.split("#")
    val userId =userInfo(0)
    val onlineYearMonth =userInfo(1)
    val semester = userInfo(3)

    userId+","+s._2.mkString(",")+onlineYearMonth+","+semester+","+s._2.indexOf(s._2.max)+","+s._2.indexOf(s._2.min)
  }


  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local[3]").setAppName("app")
    val sc=new SparkContext(conf)

      val datas=sc.textFile("D://softwares/test1.csv").map(transformDataList).reduceByKey{
        (x,y)=>
          val buffer=mutable.Buffer[Int]()
          for(i <- 0 to 23){
            buffer.append(x(i) + y(i))
          }
          buffer.toList
        }.map(transformUserNetHourList).collect()


 }
}
