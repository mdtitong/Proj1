import scala.util.control.Breaks._

object UserInputWhileLoop {
  def main(args: Array[String]): Unit = {
    println("enter 1 or 2 only")
      var flag = true
      while(flag) {
        val choice = readLine("enter choice: ")
        if(choice.equals("1") || choice.equals("2")){
          println("success")
          flag = false
        }else{
          println("try again!")
        }
      }
      println("welcome!")
  }
}
