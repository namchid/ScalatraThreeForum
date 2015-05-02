package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple.Database
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.GetResult
import scala.slick.jdbc.StaticQuery.interpolation

import Tables._

object LoginPage {

  val loginForm = {
    <form name="loginform" action="/checkLogin" method="post">
      <input type="text" id="username" name="username" placeholder="username" class="input-fields"/>
      <br/>
      <input type="password" id="password" name="password" placeholder="password" class="input-fields"/>
      <br/>
      <button id="submit-button" type="submit">login</button>
      <input type="hidden" id="hiddenfield" name="hiddenfield"/>
    </form>
  }

  val newUserForm = {
    <form name="newUserForm" action="/createNewUser" method="post">
      <input type="text" id="email" name="email" placeholder="email" class="input-fields"/>
      <br/>
      <input type="text" id="username" name="username" placeholder="username" class="input-fields"/>
      <br/>
      <input type="password" id="password1" name="password1" placeholder="password" class="input-fields"/>
      <br/>
      <input type="password" id="password2" name="password2" placeholder="re-enter password" class="input-fields"/>
      <button id="submit-button" type="submit">join the dark side.</button>
      <input type="hidden" id="hiddenfield" name="hiddenfield"/>
    </form>
  }

  def createNewUser(db: Database, email: String, username: String, password: String): Int = {
    db.withSession {
      implicit session =>
        if (users.filter(x => x.username === username).list.length == 0) {
          users.map(u =>
            (u.userEmail, u.userLevel, u.username, u.userPass)).insert(email, 1, username, password)

          val newUserCreated = users.filter(x => x.username === username).list
          return newUserCreated(0).userId
        }
        -1
    }
    -1
  }

  def set(formNum: Int = 1) = {
    val correctForm = formNum match {
      case 2 => newUserForm
      case _ => loginForm
    }
    <html lang="en">
      <head>
        <meta charset="UTF-8"/>
        <title>Matrix</title>
        <link rel="stylesheet" type="text/css" href="css/index-styles.css"/>
        <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script src="js/matrix.js" type="text/javascript"></script>
      </head>
      <body>
        <div id="php-insert"></div>
        <div id="user-form">
          <fieldset>
            <p id="forum-title">ThreeForum</p>
            <div id="form-container">
              { correctForm }
            </div>
          </fieldset>
        </div>
        <ul id="form-selector">
          <li>
            <form action="/loadLoginForm" method="post">
              <button id="login">Log in</button>
            </form>
          </li>
          <li>
            <form action="/loadNewUserForm" method="post">
              <button id="new-user">New User</button>
            </form>
          </li>
        </ul>
        <canvas id="c"></canvas>
      </body>
    </html>
  }

  def setWithError() = {
    <html lang="en">
      <head>
        <meta charset="UTF-8"/>
        <title>Matrix</title>
        <link rel="stylesheet" type="text/css" href="css/index-styles.css"/>
        <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script src="js/matrix.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/newJS/errorCreate.js"></script>
      </head>
      <body>
        <div id="php-insert"></div>
        <div id="user-form">
          <fieldset>
            <p id="forum-title">ThreeForum</p>
            <div id="form-container">
              { newUserForm }
            </div>
          </fieldset>
        </div>
        <ul id="form-selector">
          <li>
            <form action="/loadLoginForm" method="post">
              <button id="login">Log in</button>
            </form>
          </li>
          <li>
            <form action="/loadNewUserForm" method="post">
              <button id="new-user">New User</button>
            </form>
          </li>
        </ul>
        <canvas id="c"></canvas>
      </body>
    </html>
  }
}