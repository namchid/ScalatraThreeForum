package edu.cs.trinity.app

import org.scalatra._
import scala.xml.{ Text, Node }
import scalate.ScalateSupport

object Navigation {

  val profileNavigation: Seq[Node] = {
    <header>ThreeForum</header>
    <div id="cssmenu">
      <ul>
        <li class='active has-sub'>
          <a href='#todo'><span class='current'>My Profile</span></a>
          <ul>
            <li><a href='#todo'>Logout</a></li>
          </ul>
        </li>
        <li><a href='#todo'><span>Forum</span></a> </li>
        <li class='last'><a href='#todo'><span>About ThreeForum</span></a></li>
      </ul>
    </div>
  }
}