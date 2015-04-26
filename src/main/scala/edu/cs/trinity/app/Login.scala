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

object Login {

  def set(contents: Any, formSelectorContents: Seq[Node]) = {

    <html lang="en">
      <head>
        <meta charset="utf-8"/>
        <title>Matrix</title>
        <!--<link href="css/styles.css" rel="stylesheet" type="text/css"/>-->
        <link rel="stylesheet" type="text/css" href="css/index-styles.css"/>
        <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/inner-matrix.js"></script>
        <!-- <script type="text/javascript" src="resources/scripts/loadForm.js"></script>
	<script type="text/javascript" src="resources/scripts/loginForum.js"></script>
	<script type="text/javascript" src="resources/scripts/newUser.js"></script>-->
      </head>
      <body>
        <div id="php-insert"></div>
          <form id="user-form">
            <fieldset>
              <p id="forum-title">ThreeForum </p>
              <div id="form-container"></div>
            </fieldset>
          </form>
          { contents }
        {formSelectorContents}
        <canvas id="c"></canvas>
      </body>
    </html>

  }

}
