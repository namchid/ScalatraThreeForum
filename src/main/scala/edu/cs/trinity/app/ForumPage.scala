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

  val headResources = {
    <meta charset="utf-8"/>
    <title>Forum</title>
    <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" type="text/css" href="css/forum-styles.css"/>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
    <script type="text/javascript" src="js/inner-matrix.js"></script>
    <script type="text/javascript" src="js/newJS/forumBoards.js"></script>
  }

  val hiddenFormFields = generateHiddenBoardFormInputs(List("cat_name", "cat_id", "board_id", "board_name", "myPage"))

  //action was category.php. todo: look at this
  val hiddenBoardForm = {
    <form id="toCategory" action="/category" method="post">
      { hiddenFormFields }
    </form>
  }

  def generateHiddenBoardFormInputs(names: List[String]): Seq[Node] = {
    val ret = for (i <- 0 until names.length) yield {
      if (i < names.length - 1)
        <input id={ names(i) } type="hidden" style="display:none" value="x" name={ names(i) }/>
      else
        <input id={ names(i) } type="hidden" style="display:none" value="1" name={ names(i) }/>
    }
    ret
  }

  def formatBoard(name: String): Node = {
    <h2>{ name }</h2>
  }

  def getFormattedCategories(db: Database, boardId: Int, boardName: String): Seq[Node] = {
    db.withSession {
      implicit session =>
        val filteredCategories = categories.filter(x => x.boardId === boardId).list
        val ret = filteredCategories.map(c =>
          <div>
            <a class="category" title={ c.catId + "~" + c.catName + "~" + c.catId + "~" + boardName } href="#">{ c.catName }</a>
          </div>).asInstanceOf[Seq[Node]]

        ret
    }
  }

  def getBoards(db: Database): Seq[Node] = {

    db.withSession {
      implicit session =>
        val ret = boards.list.map(b => formatBoard(b.boardName) ++: getFormattedCategories(db, b.boardId, b.boardName)).asInstanceOf[Seq[Node]]
        println(ret)

        val temp = {
          <h2>technology</h2>
          <h2>stuff</h2>
        }
        //temp

        ret
    }
  }

  def set(db: Database) = {
    val boardsResult = getBoards(db)
    <html lang="en">
      <head>
        { headResources }
      </head>
      <body>
        <div id="navBar">
          { Navigation.forumNavigation }
        </div>
        <div id="mainContainer">
          <div class="subtitle">Forum Boards</div>
          <ul class="breadcrumb">
            <li><a>Forum</a></li>
          </ul>
          { hiddenBoardForm }
          { boardsResult }
          <canvas id="c" z-index="-2"></canvas>
        </div>
      </body>
    </html>
  }

}
