object libogAdminUser {
  val u_admin = "admin"
  val p_admin = "0000"
  var username = ""
  var userpw = ""
  var flag = true
  def main(args: Array[String]): Unit = {
    println("1. log-in:")
    println("2. Register:")
    while(flag){
      val uChoice = readLine("enter choice: ")
      if(uChoice.equals("1")){
        loginAccts()

      }else if(uChoice.equals("2")){
        userReg()
        loginAccts()
        flag = false
      }else{
        println("try again (1 or 2): ")
      }
    }
    println("hello world")
  }
  def userReg(): Unit ={
    println("==== registeration page =====")
    username = readLine("choose user ID: ")
    userpw = readLine("choose a password: ")
    println("registration successful, You may now log-in!")
  }
  def loginAccts(): Unit ={
    while(flag) {
      val uname = readLine("enter username: ")
      val upw = readLine("enter password: ")
      if (uname.equals(u_admin) && upw.equals(p_admin)) {
        println("this is admin")
        flag = false
      } else if (uname.equals(username) && upw.equals(userpw)) {
        println("this is user")
        flag = false
      } else {
        println("wrong username or password!")
      }
    }
  }
}
