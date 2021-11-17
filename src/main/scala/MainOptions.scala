import scala.util.control.Breaks._
import scala.io.StdIn.readLine
import java.sql.PreparedStatement
import java.sql.{Connection, DriverManager}

object MainOptions {
  var connection: Connection = null


  def mainOptions(){

    var fChoice = true
    while(fChoice){
      println("1. log-in")
      println("2. register")
      println("3. Admin log-in")

      val choice = readLine("enter option: ")
      if(choice.equals("1")){
        //log-in
        UserLogIns.connectDB()
        UserLogIns.userLogIn()
        fChoice = false
      }else if(choice.equals("2")){
        //register
        println("register page")
        val regUser = readLine("create username: ")
        val regPword = readLine("create password: ")
        UserLogIns.connectDB()
        UserLogIns.dbinsert(regUser, regPword)
        UserLogIns.userLogIn()
        fChoice = false
      }else if(choice.equals("3")){
        //admin log-in (adminID: admin, adminPW: admin)
        var adminF = true
        while(adminF) {
          val adminUname = readLine("enter admin ID: ")
          val adminPword = readLine("enter admin password: ")
          if (adminUname.equals("admin") && adminPword.equals("admin")) {
            println("Hello Admin!")
            //admin menu features
            UserLogIns.adminFeatures()
            adminF = false
          } else {
            println("wrong admin ID or password")
          }
        }
        fChoice = false
      }else{
        println("try again!")
      }
    }




  }




}
