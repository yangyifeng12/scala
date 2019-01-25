package ScalaTestOld
object test {
  def main(args: Array[String]): Unit = {
    //scala类型推导 不写类型自动匹配
    //常量不能重写
    /* val ss:Int=1
    ss=2*/
    //变量可以重写
    /*var ss:Int=2
      ss=3*/
    //字符串用==来比较
    /*val greeting = "Hello,"+"World"
    val matched = (greeting == "Hello,World")
    print(matched)*/
    //var aa:String="hello"+"world"
    //var aa="hello\nworld"
    //换行
    /*var aa=
        """hello
          |world
          |aa""".stripMargin*/

    //    var aa="="*20
    /*var a="bb"
    var aa=s"${"你看看"+"\n"+"a"*20++"\n"+"真好啊"}"*/
    /*var a="bb"
    var aa=s"${"你看看"+"\n"+"a"*20++"\n"+"真好啊"}"*/
    //整数之间相除 如果不能整除
    /*var a=15
    var aa=f"${a/8}"*/
    //整数除以小数 结果等于小数
    /*var a:Int=15
    var aa=f"${a/8.0}"*/
    //除的结果保留5位
    /*var a=15
    var aa=f"${a/1.235}%.5f"*/
    //截取字符串
    /* var a="apple"
     var aa=f"${a}%.2s"
    println(aa);*/
    //元组
    val a=(5,6, "ScalaTestOld",true,(1,2,3))
    val a1=(5,6, "ScalaTestOld",true,(1,2,4))
    var b=(a,a1)
    println(b)
    print(b._2._5._3)//4
      // print(aa)

    /* //第一种写法
     val x=5*20
     val aa=x+10
     //第二种
     val x=5*20;val aa=x+10
     //第三种
     val aa={val x=5*20;x+10}*/
    //第四种
    /*val aa={
      val x=55
      x+1
    }*/
    //    Any相当于Object
          /*//值绑定
          val message="hello"
          val statues=message match{
            case "hello"=>{
              "hello正确"
            }
            case default=>"不正确"//值绑定（case没匹配到返回default的值）其实是都可以匹配到
            case _=>()//通配符 和值绑定用法相同
          }
          print(statues)*/
//    print(aa)
      //Range左包右不包
      //for里面写两个条件，相当于循环嵌套
          /*//1
           for(x <-Range(1,3) ;y<-Range(2,4)){
              println(s"${x}${y}")
            }
          //2
          for(x<-Range(1,3)){
            for(y<-Range(2,4)){
              println(s"${x}${y}")
            }
          }*/
    /*//步长 自动减(Range(开始值，结束值，步长值))，开始值<=输出结果(根据开始值+步长值)<=结束值
    for(i<-Range(7,1,-2)){
      println(i)//753
    }
    for(i<-Range(1,7,2).reverse){
      println(i)//753
    }*/

    /*var x=10;val y=20;
    var aa=x>y match{
      case true=>x
      case false=>y
    }
    print(aa)*/
   /* val response:String = "ss"
    response match{
      case s if s !=null => println(s"Received ${response}")
      case s => println("Error! Receive a null response")
    }*/

    /*for(x<-1 to 5){
      println(x)
    }*/

    /*val days = for (x <- 1 to 7) yield {
      x
    }
    for (x <- days){
      println(x+":")
    }*/

    /*var myList = Array(1.9, 2.9, 3.4, 3.5)

    // 输出所有数组元素
    //1
    for ( x <- myList ) {
      println( x )
    }
    //2
    for ( i <- 0 to (myList.length-1)) {
        println(myList(i))
    }*/
  /* var x = 10
    while(x>0) {
      println(x)
      x = x-1
    }*/
    /* var x = 5
      do{
        println("hello")
        x-=1
      }while(x>0)
      print(x)*/

    /*//循环元祖，把他变成迭代器
    var tupl=(1,2,3)
    var a=tupl.productIterator
    for(i<-a){
      println(i)
    }*/







  }
}
