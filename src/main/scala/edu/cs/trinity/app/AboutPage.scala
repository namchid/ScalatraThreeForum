package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport

object AboutPage {

  def get() = {
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
        <!--<script type="text/javascript" src="js/setUpNavigation-about.js"></script> -->
        <script type="text/javascript" src="js/inner-matrix.js"></script>
        <title>Forum</title>
      </head>
      <body>
        <div id="navBar">{ Navigation.aboutNavigation }</div>
        <div id="mainContainer">
          <div class="subtitle">About Us</div>
          <div id="about-us-area">
            <div id="brittney">
              <div class="picture">
                <img src="images/doge.png"/>
              </div>
              <div class="about-worker-ant">
                <h5>Brittney</h5>
                <h6>The Data Hoarder</h6>
                Created database and queries.
                <br/>
                Contact on<a href="http://goo.gl/M7OB66">LinkedIn</a>
              </div>
            </div>
            <div id="hayden">
              <div class="picture">
                <img src="images/philosoraptor.png"/>
              </div>
              <div class="about-worker-ant">
                <h5>Hayden</h5>
                <h6>The Middleman</h6>
                Linked front end to back end.
                <br/>
                Contact on<a href="http://goo.gl/TEoot4">LinkedIn</a>
              </div>
            </div>
            <div id="namchi">
              <div id="inner-namchi">
                <div class="picture">
                  <img src="images/Grumpy_Cat.png"/>
                </div>
                <div class="about-worker-ant">
                  <h5>Namchi</h5>
                  <h6>The Website Beautician</h6>
                  Made the website bearable to look at.
                  <br/>
                  Contact on<a href="http://goo.gl/ka8Zy8">LinkedIn</a>
                </div>
              </div>
            </div>
          </div>
          <div id="resources-area">
            <div class="subtitle">Credits / Resources</div>
            <ul id="resources">
              <li>Fonts from <a href="http://www.google.com/fonts/">Google Fonts</a></li>
              <li>Gradient background <a href="http://cssmatic.com/gradient-generator">generator</a></li>
              <li>Background matrix based on <a href="http://goo.gl/2xa7en">this awesome tutorial</a></li>
              <li>Menu modified from <a href="http://cssmenumaker.com/menu/opera-drop-down-menu">this one</a> from CSS Menu Builder</li>
              <li>Responsive layout / media queries from <a href="http://codepen.io/Cheesetoast/pen/KFAaq?editors=110">this tutorial</a></li>
              <li>Icons made by <a href="http://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a>             is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a></li>
            </ul>
          </div>
        </div>
        <canvas id="c"></canvas>
      </body>
    </html>
  }
}
