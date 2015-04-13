package edu.cs.trinity.app

import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.H2Driver.simple._
import org.scalatra.SessionSupport //consider org.scalatra.FlashMapSupport
import java.sql.Timestamp
import scala.slick.lifted.{ProvenShape, ForeignKeyQuery}
import scala.xml._

class User(tag: Tag) extends Table[(Int, String, String, String, Timestamp, Int)](tag, "user") {
  def userid = column[Int]("user_id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("user_name")
  def userpass = column[String]("user_pass")
  def useremail = column[String]("user_email")
  def userdate = column[Timestamp]("user_date") //try Option[Timestamp]
  def userlevel = column[Int]("user_level")
  
  def * : ProvenShape[(Int, String, String, String, Timestamp, Int)] = (userid, username, userpass, useremail, userdate, userlevel)
  
}

class ThreeForumServlet(db: Database) extends ThreeforumStack with SessionSupport {

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

  get("/test") {
    <html>
      <head>
        <link rel="stylesheet" type="text/css" href="css/forum-styles.css"/>
      </head>
      <body>
				<h1>At least we know CSS works.</h1>
      </body>
    </html>
  }

  get("/session") {
    if (session.get("name").isEmpty) {
      <p>I don't know you. Let's call you Bob.</p>
    } else {
      val name = session("name")
      session.remove("name")
      <p>Welcome back { name }. I will forget you now.</p>
    }
  }

  get("/db") {
    val users = TableQuery[User]
    
    val usernames = users.map(_.username)
    println(usernames)
    
    <html>
      <body>
        <h1>You should see a database.</h1>
      </body>
    </html>
  }
  
  /** ABOUT PAGE **/
  get("/about") {
    val contents = {<h1>Todo: remove. This actually has no purpose.</h1>}
    AboutPage.setContents(contents)
  }
  
  /** PROFILE PAGE **/
  get("/profile") {
    val contents = {<h1>This is just a test. Please work.</h1>}
    ProfilePage.setContents(contents)
  }

}
