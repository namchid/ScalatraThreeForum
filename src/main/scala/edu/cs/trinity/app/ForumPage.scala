package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple._
import org.scalatra.SessionSupport
import java.sql.Timestamp
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }
import scala.slick.jdbc.GetResult
import scala.slick.jdbc.StaticQuery.interpolation

import Tables._

object ForumPage {

  def set(userid: Int) = {

    <html>
      <head>
        <body>
          <p>Welcome! Your user is { userid } </p>
          println("Welcome! Your user is{ userid }
          ")
        </body>
      </head>
    </html>
  }

  def checkUser(username: String, password: String, db: Database): Int = {
    dbQuery(db, username, password)

  }

  def dbQuery(db: Database, username: String, password: String): Int = {
    var userID = 0

    db.withSession {
      implicit session =>

        try {
          val idResult = users.filter(x => x.username === { username } && x.userpass === { password }).list
          userID = idResult(0)._1
          return userID

        } catch {
          case exp: Exception => return 0

        }

    }

  }

}
