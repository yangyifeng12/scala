package ScalaTestOld

import scala.collection.mutable

object TestCollectionNew {
  /*不可变类型	                 可变类型
  collection.immutable.List	  collection.mutable.Buffer 是一个通用的可变序列，支持在开头、中间或末尾增加元素
  collection.immutable.Set	  collection.mutable.Set
  collection.immutable.Map	  collection.mutable.Map

  collection.immutable package会自动增加到Scala的当前命名空间，但不会增加collection.mutable package。
  创建可变的集合时，要确保包含类型的完整包名。*/
  //*******************可变类型可以直接+相当于在集合后面add

  def main(args: Array[String]): Unit = {
    var nums=mutable.Buffer(1)
    for(i <- 2 to 10){
      nums +=i//往集合里面追加值
    }
    println(nums)//ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    （无默认值,指定Buffer类型）
    val numm = mutable.Buffer[Int]()
    for(i <- 2 to 10){
      numm += i
    }
    println(numm)//ArrayBuffer(2, 3, 4, 5, 6, 7, 8, 9, 10)
    /*利用toList方法，可以随时把可变的缓冲区转换回不可变的列表。
    可以把Buffer类型的重新转换成List类型的*/
    /*从不可变集合创建可变集合 先把不可变集合转换成可变集合再插入值，之后再转换成不可变集合
    def coll() = {
      val m = Map("AAPL" -> 597,"MSFT" -> 40)
      val n = m.toBuffer
      n.remove(1)
      n.+=("GOOG" -> 521)
      n.toMap
    }*/
//    要为一个特定的集合类型创建构造器，可以调用这个类型的newBuilder方法，并指定集合'元素的类型。
    def nb() = {
      val b = Set.newBuilder[Char]
      b += 'h'
      b ++= List('e','l','l','o')
      b.result()
    }
    println(nb())
//    Array是一个长度大小固定的可变元素值的数组。
    val colors = Array("red","green","blue")
    colors(0) = "purple"
   for(i<-colors) print(i)//purple green blue
    println()
    /*Scala Option(选项)类型用来表示一个值是可选的（有值或无值)。
    Option[T] 是一个类型为 T 的可选值的容器： 如果值存在， Option[T] 就是一个 Some[T] ，如果不存在， Option[T] 就是对象 None */
    var x:String="sssss"
    var a=Option(x)
    print(a)
    //一元集合默认值是none
    //fold 让默认值none变成别的
    //getOrElse 让默认值none变成别的

    //    在类的参数列表里面写的参数相当于 类里面的属性

  }
}
