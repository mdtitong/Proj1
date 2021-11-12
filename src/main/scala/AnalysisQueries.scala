object AnalysisQueries {
  var flag = true
  def main(args: Array[String]): Unit = {
    println("===== nba salaries (season 2017-2020) =====")
    println("============================================")
    println("1. Top 10 Highest Salary for the year 2020")
    println("2. Top 10 Lowest Salary for the year 2020")
    println("3. Top 10 Highest earners from the year 2017 to 2020")
    println("4. Top 10 Lowest earners from the year 2017 to 2020")
    println("5. Highest Earning NBA player for the year 2017-2020")
    println("6. Salary prediction for next season for the highest-earning NBA player")
    println("=============================================")

    while(flag){
      val menu = readLine("enter number of choice: ")
      if(menu.equals("1")){
        println("option 1")
        flag = false
      }else if(menu.equals("2")){
        println("option 2")
        flag = false
      }else if(menu.equals("3")) {
        println("option 3")
        flag = false
      }else if(menu.equals("4")){
        println("option 4")
        flag = false
      }else if(menu.equals("5")){
        println("option 5")
        flag = false
      }else if(menu.equals("6")){
        println("option 6")
        flag = false
      }else{
        println("try again!")
      }
    }
    println("welcome page")
  }

}
