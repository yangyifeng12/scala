package ScalaTestOld

object TestCollections {
  def main(args: Array[String]): Unit = {
    //Nil相当于空列表{ } List()=Nil
    //可以用head()和tail()方法分别访问一个列表的首元素和其余元素。
    var colors=List("ScalaTestOld","bb","cc")
    println("head为"+colors.head)
    println("tail为"+colors.tail)
    // 要直接访问单个元素，可以作为一个函数来调用这个列表，并传入该元素的索引（从0开始）
    println(colors(0))//aa

    //  List：一个不可变的单链表。可以作为一个函数调用List来创建一个列表，并以逗号分隔参数的形式传入列表的内容：
    val number=List(1,2,3,4,5)
    var c=List("ScalaTestOld","bb","cc")
    //长度和输出
    println(s"${number.size}${number}")
    //    for迭代列表
    for(a<-number){
      println(a)
    }
    number.foreach((c:Int)=>println(c))
    number.foreach(println)
//map()：取一个函数，将一个列表元素转换为另一个值和/或类型的列表。
    println(number.map(_ +2))//每一个元素加2
//    reduce()：取一个函数，将两个列表元素结合为一个元素。
      var list = number.reduce((a:Int,b:Int)=>a + b)
      var list1 = number.reduce(_+_)//调用了一次可以用下划线替代
      println(s"reduce结果为${list1}")
//    filter():函数过滤器 返回值为布尔值的
      println(number.filter(_%2==0))//%取余
//    Set:是一个不可变的无序集合，只包含不重复的唯一元素，不过其工作与List类似。
    val unique = Set(10,20,30,20,20,10)
    println( unique.reduce( _+_ ))//60去重复的
/*    Map:是一个不可变的键/值库，在其他语言中也称为散列映射、字典或关联数组。
    在Map中，值按一个给定的唯一键存储，可以使用这个键来获取相应的值。*/
    val colorMap=Map("白色"->"white","红色"->"red","a"->1,"b"->2)
    println(colorMap("白色"))//java中用个get(key)取
    /*println(colorMap("a") | colorMap("b"))//0为假1为真*/
    println(colorMap.contains("d"))//Map里面是否包含key
    /*Cons（construct）操作符
    使用Nil作为基础，并使用右结合的cons操作符：：绑定元素，就可以构建一个列表，而不必使用传统的List(...)格式。*/
    //::只能3::first这种形式的，只能往最左边添加
    var first=1 ::2::Nil
    println(first)//List(1,2)
    //.::相当于往最左面添加元素
    println(Nil.:: (1).::(2))//List(2,1)相当于往最左面添加元素
    var Second=3::first
    println(Second)//在已经有值的List集合前面添加一个元素，最后因为是Nil不能添加
    println(Second.tail==first)//因为Second是往First最前面追加的元素，所以用tail去掉最前面的，还等于First
    println("first"+first.::(3))
//:::在列表前面追加列表。
     var numl=List(1,2)
    println(List(3):::numl)//List(3, 1, 2)
    println(numl:::List(3))//List(1, 2, 3)
// ++为列表追加另一个***集合*** ,以前面的集合类型为主
       println(Set(3,4)++first)//Set(3, 4, 1, 2)
       println(first++Set(3,4))//List(1, 2, 3, 4)
//==  判断两个集合内容相等  List(1,2) == List(1,2)
//distinct返回不包含重复元素的列表版本
    println(List(1,1,1).distinct)
//drop 从列表中去除前n个元素
    println(numl.drop(1))
    //***********三段式 num1 drop 1
//从列表返回过滤后的元素
    println(numl.filter(_>=1))
//将一个列表的列表转换为元素列表
    println(List(List(1,2),List(3,4)).flatten)//List(1, 2, 3, 4)
//partition将元素分组为两个列表构成的元组
    println(List(1,2,3,4).partition(_>2))//(List(3, 4),List(1, 2))
//reverse	列表反转
    println(List(1,2,3).reverse)//List(3,2,1)
//slice	返回第一个和第二个索引中间部分
    println(List(1,2,3,4,5).slice(2,4))//List(3, 4) slice()前包后不包
//sortBy	按给定函数返回的值对列表排序
    println(List("apple","to") sortBy(_.size))//List(to, apple)

//sorted	按自然值对核心Scala类型列表排序
    println(List("apple","to").sorted)//List(apple, to)
    println(List(2,41,12).sorted(Ordering.Int.reverse))//List(41,12,2)正顺序之后反转
//sortWith
    println(List("apple","to").sortWith(_>_))//List(apple, to)
    println(List(5,6,2).sortWith(_>_))//List(6,5,2)
    println(List(5,6,2).sortWith(_<_))//List(2, 5, 6)
//splitAt	按数字个数拆分成两个列表
    println(List(2,3,5,7) splitAt 2)//(List(2, 3),List(5, 7))
//take	从列表抽取前n个元素(读取前n个元素)
    println(List(2,3,5,7,11,13) take 3)//List(2, 3, 5)
//    zip	合并为一个一一对应的元组列表
    println(List(1,2) zip List("a","b"))//List((1,a), (2,b))

  }
}
