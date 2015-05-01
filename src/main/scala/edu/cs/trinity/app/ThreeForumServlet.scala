package edu.cs.trinity.app

import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple._
import org.scalatra.SessionSupport
import java.sql.Timestamp
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }
import scala.slick.jdbc.GetResult
import scala.slick.jdbc.StaticQuery.interpolation

import Tables._

class ThreeForumServlet(db: Database) extends ThreeforumStack with SessionSupport {

  def checkUserLogin(username: String, password: String): Int = {
    db.withSession {
      implicit session =>
        val filteredUsers = users.filter(x => x.username === username && x.userPass === password).list
        if (filteredUsers.length < 1) redirect("/")
        else return filteredUsers(0).userId
    }
    -1
  }

  get("/") {
    session.get("userId") match {
      case None =>
        LoginPage.set()
      case _ =>
        redirect("/forum")
    }
  }

  get("/newUserPage") {
    LoginPage.set(2)
  }

  get("/about") {
    AboutPage.get()
  }

  get("/profile") {
    if (session.get("userId") == None) redirect("/")

    val username = session("username")
    val form = {
      <form id="jumpToTopic" action="/posts" method="post">
        <input id="topic_id" type="hidden" style="display:none" value="x" name="topic_id"></input>
        <input id="page" type="hidden" style="display:none" value="1" name="page"></input>
      </form>
    }
    ProfilePage.set(db, { session("userId").asInstanceOf[Int] }, form)
  }

  post("/loadNewUserForm") {
    redirect("/newUserPage")
  }

  post("/loadLoginForm") {
    redirect("/")
  }

  post("/checkLogin") {
    (params.get("username"), params.get("password")) match {
      case (None, None) =>
        redirect("/")
      case _ =>
        session("userId") = checkUserLogin(params("username"), params("password"))
        session("username") = params("username")
        redirect("/forum")
    }
  }

  get("/forum") {
    if (session.get("userId") == None) redirect("/")
    println("\nyou are in forum\n" + { session("userId") })

    ForumPage.set(db)
  }

  get("/logout") {
    session.invalidate
  }

  post("/category") {
    <html>
      <body>
        This is actually Hayden's page
        <br/>
        Parameters:
        { params }
      </body>
    </html>
  }
}
