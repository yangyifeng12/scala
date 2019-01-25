package ScalaTestOld

import java.util

import collection.JavaConverters._

object TestCollections2 {
  def main(args: Array[String]): Unit = {
    var a = List(1,2,3,4)
    var b = List("a","b","c")

    //::反向操作 :+ 向最右边插入元素
    println(List(1,2,3,4) :+ 5)//List(1,2,3,4,5)
    //take反向操作takeRight
    println(a takeRight 3)//List(2, 3, 4)
    //drop反向操作 dropRight
    println(a dropRight 2)//List(1, 2)
    /*映射列表
    映射方法是指取一个函数，将它应用于列表的每一个成员，再把结果收集到一个新列表。使得另一个列表与第一个列表有相同的大小，但是有不同的数据或元素类型。*/
    println(List(0,1,0) collect {case 1 => "ok"})//List(ok)
    println(List("milk","tea") flatMap(_.split(',')))//List(milk,tea)
    println(List("milk","tea") map(_.toUpperCase))//List(MILK,TEA)
    //数学归约操作
      //max		查找列表中的最大值
      /*min	List(10.9,32.5,4.23,5.67).min	查找列表中的最小值
      product	List(5,6,7).product	将列表中的数相乘
      sum	List(11.3,23.5,7.2).sum	对列表中的数求和*/
     // 布尔归约操作
       /* contains	List(34,29,18) contains 29	检查列表中是否包含这个元素
        endsWith	List(0,4,3) endsWith List(4,3)	检查列表是否以一个给定列表结尾
        exists	List(24,17,32) exists (_ < 18)	检查至少对列表中的一个元素返回true
        forall	List(24,17,32) forall (_ < 18)	检查至少对列表中的元素都返回true
        startsWith	List(0,4,3) startsWith List(0)	检查列表是否以一个给定的列表开头*/
//        通用列表归约操作
    println(List(4,5,6).scanRight(0)(_-_))
       /*
       //算法一样fold和foldLeft
        fold	List(4,5,6).fold(0)(_-_)	给定起始值和归约函数来归约列表//0+4 4+5 5+6
        foldLeft	List(4,5,6).foldLeft(0)(_-_)	给定起始值和归约函数从左到右归约列表
        foldRight	List(4,5,6).foldRight(0)(_-_)	给定起始值和归约函数从右到左归约列表//6-0 5-6 4-(-1) 5

    reduce	List(4,5,6).reduce(_+_)	给定一个归约函数，从列表中第一个元素开始归约列表
    reduceLeft	List(4,5,6).reduceLeft(_+_)	给定一个归约函数，从列表中第一个元素开始从左到右归约列表
    reduceRight	List(4,5,6).reduceRight(_-_)	给定一个归约函数，从列表中第一个元素开始从右到左归约列表

    scan	List(4,5,6).scan(0)(_+_)	取一个起始值和归约函数，返回各个累加值的一个列表 List(0, 4, 9, 15)
    scanLeft	List(4,5,6).scanLeft(0)(_+_)	取一个起始值和归约函数，从左到右返回各个累加值的一个列表
    scanRight		取一个起始值和归约函数，从右到左返回各个累加值的一个列表
    println(List(4,5,6).scanRight(0)(_-_)) //List(5, -1, 6, 0)*/

//      mkString		使用给定分隔符将一个集合呈现为String
      List(24,99,104).mkString("{",",","}")
      //toBuffer		将不可变的集合转换为可变的集合
    var p=a.toBuffer
    p.insert(1,123)
      println(a.toBuffer.insert(1,123))//ArrayBuffer(1, 123, 2, 3, 4)
      //toList		将集合转换为List
        Map("a"->1,"b"->2).toList
//      toMap	Set(1->true,3->true).toMap	将长度为2的元组的集合转换为Map
//      toSet	List(2,5,5,3,2).toSet	将集合转换为Set
//      toString		将集合成String，包括集合的类型(把所有的全变促成字符串了)
    println(List(2,5,5,3,2).toString)//List(2, 5, 5, 3, 2)

    //*********别忘了import collection.JavaConverters._
    var jlist=a.asJava//把scala转换成java
    println(jlist.get(0))//1
    var m=new util.ArrayList[Int]()
    println(m.getClass.getName)//java.util.ArrayList
    var list=jlist.asScala//把java转换成scala
    println(list.reduce(_+_))



  }
}
