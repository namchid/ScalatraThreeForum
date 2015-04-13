package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport

object ProfilePage {

  def setContents(content: Seq[Node]) = {
    <html lang="en">
      <head>
        <meta charset="utf-8"/>
        <link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'/>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" tpye="text/css" href="css/forum-styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/about-styles.css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/setUpNavigation-about.js"></script>
        <script type="text/javascript" src="js/inner-matrix.js"></script>
        <title>Forum</title>
      </head>
      <body>
        <div id="navBar"></div><!--todo: this was set up by ajax+php before. -->
        <div id="mainContainer">
          <div class="subtitle">About Us</div>
          { content }<!-- todo: check if this works -->
        </div>
      </body>
    </html>
  }
}