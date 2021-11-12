import org.apache.spark.sql.SparkSession


object Main {
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "C:\\hadoop")

    val spark = SparkSession
      .builder
      .appName("hello hive")
      .config("spark.master", "local")
      .enableHiveSupport()
      .getOrCreate()
    println("created spark session")
    spark.sql(sqlText = "DROP TABLE IF EXISTS nbaTable")
    spark.sql("CREATE TABLE IF NOT EXISTS nbaTable" +
      "(rank INT,name STRING,position STRING,team STRING,salary BIGINT,season STRING) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql("LOAD DATA LOCAL INPATH 'input/p1_nbaSalaries.txt' INTO TABLE nbaTable")
//    spark.sql("SELECT * FROM nbaTable").show(20)

    //1. Top 10 Highest Salary for the year 2020
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem1")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem1(playerName STRING, season2020 BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem1(SELECT name, salary " +
      "FROM nbaTable WHERE season = '2020' ORDER BY salary DESC LIMIT 10)")
    spark.sql("SELECT * FROM problem1").show()

    //2. Top 10 Lowest Salary for the year 2020
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem2")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem2(playerName STRING, season2020 BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem2(SELECT name, salary FROM nbaTable WHERE season = '2020' " +
      "ORDER BY salary LIMIT 10)")
    spark.sql("SELECT * FROM problem2").show()

    //3. Top 10 Highest earners from the year 2017 to 2020
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem3")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem3(playerName STRING, season17_20 BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem3(select name, sum(salary) as total from nbaTable " +
      "group by name order by total desc limit 10)")
    spark.sql("SELECT * FROM problem3").show()

    //4. Top 10 Lowest earners from the year 2017 to 2020
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem4")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem4(playerName STRING, season17_20 BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem4(select name, sum(salary) as total from nbaTable " +
      "group by name order by total limit 10)")
    spark.sql("SELECT * FROM problem4").show()

    //5. Highest Earning NBA player for the year 2017-2020
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem5")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem5(playerName STRING, season17_20 BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem5(select name, sum(salary) as total from nbaTable " +
      "group by name order by total desc limit 1)")
    spark.sql("SELECT * FROM problem5").show()

    //6. Salary prediction for next season for the highest-earning NBA player
    spark.sql(sqlText = "DROP TABLE IF EXISTS problem6")
    spark.sql(sqlText = "CREATE TABLE IF NOT EXISTS problem6" +
      "(playerName STRING, position STRING, team STRING, prediction BIGINT) " +
      "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','")
    spark.sql(sqlText = "INSERT INTO TABLE problem6(select name, position, team, " +
      "cast(round((((37436858-35654150)/35654150) * 37436858) + 37436858, 0) as int) as prediction " +
      "from nbaTable where name = 'LeBron James' limit 1)")
    spark.sql("SELECT * FROM problem6").show()


  }
}
