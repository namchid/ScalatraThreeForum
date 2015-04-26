package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple._
import org.scalatra.SessionSupport
import scala.slick.jdbc.GetResult
import scala.slick.jdbc.StaticQuery.interpolation

import Tables._

object ProfilePage {

  def databaseQueries(db: Database, userId: Int) = {
    var username = ""
    var userEmail = ""
    var userPostCount = 0
    var lastPostDate = ""
    var lastTenPosts: Seq[Node] = Nil

    db.withSession {
      implicit session =>
        val filteredUsers = (users.filter(x => x.userid === userId).list)
        val correctUser = filteredUsers(0)
        val postsHistory = posts.filter(x => x.userId === userId).list

        username = correctUser._2
        userEmail = correctUser._4
        userPostCount = postsHistory.size

        userPostCount match {
          case 0 =>
          case _ =>
            val lastPost = postsHistory.last
            val lastTenPostsHistory = postsHistory.takeRight(10).map(x => x._4).toList.reverse
            val map = topicsMap.toMap

            lastPostDate = lastPost._3.toString().split(" ")(0)
            lastTenPosts = lastTenPostsHistory.map(x =>
              <div class="history">{ username } posted in <a class="jump_to_topic" title={ x.toString() } href="#">{ map(x); }</a></div>).asInstanceOf[Seq[Node]]
        }

    }
    (username, userEmail, userPostCount, lastPostDate, lastTenPosts)
  }

  def set(db: Database, userId: Int, form: Seq[Node]) = {
    val res = databaseQueries(db, userId)

    <html lang="en">
      <head>
        <meta charset="utf-8"/>
        <title>Forum</title>
        <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'/>
        <link rel="stylesheet" type="text/css" href="css/forum-styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/profile-styles.css"/>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/inner-matrix.js"></script>
        <script type="text/javascript" src="js/profile.js"></script>
        <script type="text/javascript" src="js/jumpToTopic.js"></script>
      </head>
      <body>
        <div id="navBar">
          { Navigation.profileNavigation }
        </div>
        <div id="mainContainer">
          <div class="subtitle"> { res._1 } </div>
          <div id="profile-area">
            <div id="left-panel">
              <div id="stats-area">
                <table id="user-stats-table">
                  <tr>
                    <td class="col-one">Email:</td>
                    <td>{ res._2 }</td>
                  </tr>
                  <tr>
                    <td class="col-one">Total Posts:</td>
                    <td>{ res._3 }</td>
                  </tr>
                  <tr>
                    <td class="col-one">Last Post:</td>
                    <td>{ res._4 }</td>
                  </tr>
                </table>
              </div>
            </div>
            <div id="right-panel">
              <div id="about-user-area">
                <h4>
                  About
                </h4>
                Lorem Ipsum here.
              </div>
              <div id="post-history">
                <h4>Recent Posts</h4>
                { form }
                { res._5 }
              </div>
            </div>
          </div>
        </div>
        <canvas id="c"></canvas>
      </body>
    </html>
  }
}
