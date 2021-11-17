import scala.io.StdIn.readLine
import java.sql.PreparedStatement
import java.sql.{Connection, DriverManager}

object UserLogIns {
  var connection: Connection = null

  //connection to mySQL
  def connectDB(): Unit ={

    val url = "jdbc:mysql://localhost:3306/demodatabase"
    val driver = "com.mysql.cj.jdbc.Driver"
    val unDB = "root"
    val pwDB = "root"

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, unDB, pwDB)

    } catch {
      case e: Exception => e.printStackTrace
    }
  }
  //method: user log-in
  def userLogIn() {
    var flag = true
    val statement = connection.createStatement
    while(flag) {
      val logUser = readLine("enter username: ")
      val logPw = readLine("enter password: ")
      val rs = statement.executeQuery("SELECT * FROM users WHERE username ='"+logUser+"' AND password = '"+logPw+"'")

      if(rs.next()){
        println("Hello "+ logUser)
        flag = false
      }else{
        println("Wrong username or password!")
      }
    }
    statement.close()
  }
  //insert registered userID and password
  def dbinsert(username: String, password: String) {

    try {
      val insertSql = "INSERT INTO users (username, password) VALUES (?,?);"

      val preparedStmt: PreparedStatement = connection.prepareStatement(insertSql)

      preparedStmt.setString (1, username)
      preparedStmt.setString (2, password)

      preparedStmt.execute
      preparedStmt.close()

    } catch {
      case e: Exception => e.printStackTrace
    }

  }
  //admin feature menu
  def adminFeatures(): Unit ={
    println("1. view NBA Salaries")
    println("2. view users")

    val adChoice = readLine("Enter option: ")
    if(adChoice.equals("1")){
      println("Welcome to NBA Salaries!")
    }else if(adChoice.equals("2")){
      connectDB()
      viewDB()
    }
  }
  //method: admin feature to view users credentials
  def viewDB() {

    val statement = connection.createStatement

    val rs = statement.executeQuery("SELECT * FROM users")

    println("+==========+==========+")
    println("| username | password |")
    println("+==========+==========+")

    while (rs.next) {
      val username = rs.getString("username")
      val password = rs.getString("password")
      println("|%-10s| %-9s| ".format(username, password))
    }
    statement.close()
  }

}
