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

  get("/posts") {
    if (session.get("userId") == None) redirect("/")
    Posts.SetPassed(params, db, session("userId"))
  }

  post("/AddPost") {
    if (session.get("userId") == None) redirect("/")
    val content = request.body
    Posts.Add(params, db, session("userId"))

    //println("Your params: " + params)
    redirect("/posts?topic_id=" + params.get("topic_id_post").get + "&page=" + params("page_post"))
    //Posts.SetPassed(params, db, session("userId"))
  }

  get("/categories") {
    if (session.get("userId") == None) redirect("/")
    Categories.GoCategories(params, db, session("userId"))
  }
  post("/category") {
    if (session.get("userId") == None) redirect("/")
    val content = request.body
    <div> redirect not working? Above</div>
    var url = "/categories?cat_id="
    url += params.get("cat_id").get
    redirect(url)
    //    redirect("/categories?cat_id="+params.get("cat_id"))
    <div> redirect not working? Below</div>
  }

  get("/newUserPage") {
    if (session.get("userId") == None) redirect("/")
    LoginPage.set(2)
  }

  get("/about") {
    if (session.get("userId") == None) redirect("/")
    AboutPage.get()
  }

  get("/profile") {
    if (session.get("userId") == None) redirect("/")

    val username = session("username")
    val form = {
      <form id="jumpToTopic" action="/hackPosts" method="post">
        <input id="topic_id" type="hidden" style="display:none" value="x" name="topic_id"></input>
        <input id="page" type="hidden" style="display:none" value="1" name="page"></input>
      </form>
    }
    ProfilePage.set(db, { session("userId").asInstanceOf[Int] }, form)
  }

  post("/hackPosts") {
    val topicId = params("topic_id")

    redirect("/posts?topic_id=" + { topicId })
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

  post("/createNewUser") {
    val email = params("email")
    val username = params("username")
    val password = params("password1")
    val password2 = params("password2")

    if (email == "" || username == "" || password == "" || password2 == "" || (password != password2))
      redirect("/formErrors")
    else {
      val newUserCreated = LoginPage.createNewUser(db, email, username, password)
      if (newUserCreated > -1) {
        //println("true")
        session("userId") = newUserCreated
        session("username") = username
        redirect("/forum")
      } else {
        redirect("/formErrors")
      }
    }
  }

  get("/formErrors") {
    LoginPage.setWithError()
  }

  get("/createNewUser") {
    redirect("/newUserPage")
  }

  get("/forum") {
    if (session.get("userId") == None) redirect("/")
    //println("\nyou are in forum\n" + { session("userId") })

    ForumPage.set(db)
  }

  get("/logout") {
    session.invalidate
    redirect("/")
  }

}
