package ScalaTestOld

object DefTest {
  //空为（）相当与null
  //return在函数中代表退出显示。。。。
  //********************函数别忘写等号******************************
  def c(a:Int,b:Int)={
    a+b
  }
  //在Scala中，还可以按名调用参数，这样就允许不按顺序指定参数。
  //默认参数（参数有默认值的）最好写在参数列表的最后面
  def  ordera(b:Int,a:Int=1)={
      s"${b}\t${a}"
  }
  //可变参数 在函数定义中需要该参数类型后面增加一个星号
  //************可变参数参数不能有默认值***********
  def sum(items:Int*) ={//多个类型是Int的参数
    var total = 0
    for(i <- items) {
      total += i
    }
    total
  }
  def sumUpGrade(a:String,b:Int,items:Int*)= {//多个类型是Int的参数
    print(s"${a}\t${b}\t")
  var total = 0
    for(i <- items) {
      total += i
    }
    total
  }
//  可以把参数表分解为参数组，每个参数组分别用小括号分隔
def sumUpGradeC(a:String)(b:Int)(items:Int*)= {//多个类型是Int的参数
  print(s"${a}\t${b}\t")
var total = 0
  for(i <- items) {
    total += i
  }
  total
}
  //符合函数
  def safeString(s:String,f:(String)=>String)={
    if(s==null) null else f(s)
  }
  def reverser(s:String) = s.reverse
//柯里化   ***************************不要忘记写参数的类型
// 重用一个函数调用，而且希望保留一些参数不想再次输入
// 如果想保留一些参数，可以部分应用这个函数，使用通配符替代其中一个参数。
  def aa(x:Int,y:Int) ={
      x+y
  }
  val bb=aa(1,_:Int)
  var bbt=aa(_:Int,_:Int)
  //参数组
  def factoror(x:Int)(y:Int) = y / x
  val isEven = factoror (2)(_:Int)
  //传值调用（call-by-value）先计算参数表达式的值(reduce the arguments)，再应用到函数内部；
  //传名调用（call-by-name）未计算的参数表达式直接应用到函数内部。现用现算
 // => Unit是 传名函数, 只传入了一个表达式, 在调用时才会去执行, 使用 code调用() => 是传值函数, 传入的计算后的值, 使用 code() 调用
  //传名调用
  var flag: Boolean = true
  def useOrNotUse(x: Int, y: => Int) = {
    flag match{
      case true => x
      case false => x + y
    }
  }
    def getTime():Long={
      println("获得时间")
      System.currentTimeMillis()

    }
  //传值调用
    def delayTime(f:()=>Long)={
      println("start...")
      println(f())
      println("stop...")
    }
  //传名调用*************注意空格分割参数列表里面的符号*************
  def delatTime(f : =>Long) ={
    println("start...")
    println(f)
    println("stop...")
  }








  def main(args: Array[String]): Unit = {
    /*println(ordera(3))//3 1 上面函数中a已经赋值了，不过不重新赋值就是原来的值
    println(ordera(a=2,b=3))//3 2
    println(sum(1,2,3,4,5))//15
    println(sumUpGrade("aa",2,3,4,5))//aa 2 12
    println(sumUpGradeC("aa")(2)(3,4,5))//aa 2 12
    println(safeString("ready",reverser))//ready ydaer
    println(bb(1))2
    println(bbt(1,2))3
    println(isEven(4))2
   println(useOrNotUse(1, 2))1
    flag = false
    println(useOrNotUse(1, 2))3*/
   delayTime{println("11111");getTime}
    delatTime{println("11111");getTime()}





  }
}
