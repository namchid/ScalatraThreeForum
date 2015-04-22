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

  def databaseQueries(db: Database, username: Any) = {
    val name = username
    val recentLimit = 10
    val userId = 1

    var userEmail = ""
    var userPostCount = -1
    var lastPostDate = ""
    var postHistory: Seq[Node] = Nil

    db.withSession {
      implicit session =>
        val emailRes = users.filter(x => x.userid === { userId }).list
        val postsRes = posts.filter(x => x.userId === { userId }).list
        val lastPost = postsRes.last

        lastPostDate = lastPost._3.toString().split(" ")(0)
        userEmail = emailRes(0)._4
        userPostCount = postsRes.size

        val topicHistoryIds = postsRes.takeRight(10).map(x => x._4).toList.reverse

        val map = topicsMap.toMap
        postHistory = topicHistoryIds.map(x =>
          <div class="history">{ name } posted in <a class="jump_to_topic" title={ x.toString() } href="#">{ map(x); }</a></div>).asInstanceOf[Seq[Node]]

    }

    { (userEmail, userPostCount, lastPostDate, postHistory) }
  }

  def set(db: Database, username: Any, content: Seq[Node]) = {
    val databaseResults = databaseQueries(db, username)

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
          <div class="subtitle"> { username } </div>
          <div id="profile-area">
            <div id="left-panel">
              <div id="stats-area">
                <table id="user-stats-table">
                  <tr>
                    <td class="col-one">Email:</td>
                    <td>{ databaseResults._1 }</td>
                  </tr>
                  <tr>
                    <td class="col-one">Total Posts:</td>
                    <td>{ databaseResults._2 }</td>
                  </tr>
                  <tr>
                    <td class="col-one">Last Post:</td>
                    <td>{ databaseResults._3 }</td>
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


{content}

                { databaseResults._4 }
              </div>
            </div>
          </div>
        </div>
        <canvas id="c"></canvas>
      </body>
    </html>
  }
}
