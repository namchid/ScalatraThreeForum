package edu.cs.trinity.app

import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple._
import org.scalatra.SessionSupport
import java.sql.Timestamp
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }
import scala.slick.jdbc.GetResult
import scala.slick.jdbc.StaticQuery.interpolation

class User(tag: Tag) extends Table[(Int, String, String, String, Timestamp, Int)](tag, "users") {
  def userid = column[Int]("user_id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("user_name")
  def userpass = column[String]("user_pass")
  def useremail = column[String]("user_email")
  def userdate = column[Timestamp]("user_date") //try Option[Timestamp]
  def userlevel = column[Int]("user_level")

  def * : ProvenShape[(Int, String, String, String, Timestamp, Int)] = (userid, username, userpass, useremail, userdate, userlevel)

  //def * = (userid, username, userpass, useremail, userdate, userlevel)
}

case class User2(id: Int, name: String, pass: String, email: String, date: Timestamp, level: Int)

class ThreeForumServlet(db: Database) extends ThreeforumStack with SessionSupport {
  val users = TableQuery[User]

  implicit val getUser2 = GetResult(r => User2(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say<a href="hello-scalate">hello to Scalate</a>
        .
        <h2>ThreeForum</h2>
      </body>
    </html>
  }

  get("/about") {
    AboutPage.get()
  }

  get("/profile") {
    //future todo: "/posts" should be whatever link hayden has. posts.php --> /posts?
    //future todo: actually get the real session("username")
    session("username") = "hermajesty"
    val username = session("username")
    val content = {
      <form id="jumpToTopic" action={ url("/posts") } method="post">
        <input id="topic_id" type="hidden" style="display:none" value="x" name="topic_id"></input>
        <input id="page" type="hidden" style="display:none" value="1" name="page"></input>
      </form>
    }
    
    ProfilePage.set(db, username, content)
  }

  get("/session") {
    if (session.get("name").isEmpty) {
      session("name") = "Bob"
      <p>I don't know you. Let's call you Bob.</p>
    } else {
      val name = session("name")
      session.remove("name")
      <p>Welcome back { name }. I will forget you now.</p>
    }
  }

  get("/db") {
    println("no")
    db.withSession {
      implicit session =>
        println(users.selectStatement)
        val sel = sql"SELECT * FROM users"
        println(sel.as[User2].list)
        println(users.list)
      //        val ts = new java.sql.Timestamp(new java.util.Date().getTime)
      //        users += (100, "Bobby", "fischer", "bfish@fish.com", ts, 1)
      // val usernames = for { u <- users } yield u.username

      //(users.ddl).create

      //        val usernames = users.map(_.username)
      //        println(usernames)
    }

    // Int, String, String, String, Timestamp, Int
    //implicit val getUsersResult = 
    //  GetResult(u => User(u.nextInt, u.nextString, u.nextString, u.nextString, u.nextTimestamp, u.nextInt))

    <html>
      <body>
        <h1>You should see a database.</h1>
      </body>
    </html>
  }

}
