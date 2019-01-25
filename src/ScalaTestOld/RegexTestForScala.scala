package ScalaTestOld

import scala.util.matching.Regex


object RegexTestForScala {
  def main(args: Array[String]): Unit = {

    var strings = "cac_test_haha";
    var reg = "^[a-z]{3}_".r ;

    println((reg findFirstIn  strings)).toString//findAllIn是全局匹配

  }
}
