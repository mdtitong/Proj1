import scala.util.control.Breaks._

object RegisterAndLogins {
  var flag = true
  def main(args: Array[String]): Unit = {
    val uname = ""
    val pword = ""

    println("welcome to login page!")
    println("1. log-in")
    println("2. register")
    while(flag){
    val rlChoice = readLine("enter choice:")
    if(rlChoice.equals("2")){
      register()
    }else if(rlChoice.equals("1")) {
        admin()
      println("thank you!")
      flag = false
      }else{
        println("try again! choose 1 or 2")
      }
    }
    println("welcome to nba salary")
  }
  def admin(): Unit ={

    while(flag){
      println("==== log-in ====")
      val regName = readLine("enter username:  ")
      val regPW = readLine("enter password: ")
      if (regName.equals("admin") && regPW.equals("0000")) {
        println("welcome admin!")
        println("login successful!")
        flag = false
      } else {
        println("wrong username or password!")
      }
    }
  }
  def user(): Unit ={

  }
  def register(): Unit ={
    val uname = readLine("create username: ")
    val pword = readLine("create password: ")

    println("thank you for registering!")
    while(flag){
      println("==== log-in ====")
      val regName = readLine("enter username:  ")
      val regPW = readLine("enter password: ")
      if (regName.equals(uname) && regPW.equals(pword)) {
        println("welcome user!")
        println("login successful!")
        flag = false
      } else {
        println("wrong username or password!")
      }
    }
//    admin()
  }

}
