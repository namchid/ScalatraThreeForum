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


object Categories {

    implicit val getUser2 = GetResult(r => User2(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
    implicit val getBoard2 = GetResult(r => Board2(r.<<, r.<<, r.<<))
    implicit val getCat2 = GetResult(r => Cat2(r.<<, r.<<, r.<<, r.<<))
  
  
  def GoCategories(params:org.scalatra.Params, db:Database, user_id:Any)={
    
        var userID = user_id
        var cat_id = myG(params.get("cat_id")).toInt
    //    var cat_id = 1
        
        var topicSeq = scala.xml.NodeSeq.Empty //null.asInstanceOf[Seq[scala.xml.Node]]
        var catRes:String = ""
        
        db.withSession{
          implicit session =>
           var slickJoin =  for {
             c <- categories if c.catId === cat_id
//            } yield LiteralColumn ("") ++ c.catId ++ "~*~" ++ c.catName ++ "~*~" ++ c.catDesc ++ "~*~" ++ c.boardId
           } yield LiteralColumn ("") ++ c.catId.asColumnOf[String] ++ "~x~" ++ c.catName ++ "~x~" ++ c.catDesc ++ "~x~" ++ c.boardId.asColumnOf[String]
           // } yield( "" + c.catId.asColumnOf[Int] + "~x~" + c.catName.asColumnOf[String] + "~x~" + c.catDesc.asColumnOf[String] + "~x~" + c.boardId.asColumnOf[Int])
            // FUCK slick. NOTHING about it is even remotely fucking usable . 
            slickJoin.foreach{e =>
               catRes = catRes + e
             } 
          }
        val catSplit = catRes.split("~x~")
        println(catSplit)
        var boardName:String = ""
        db.withSession{
          implicit session =>
            var boardRes = for {
              b <- boards if b.boardId === catSplit(3).toInt
            }       yield (b.boardName.asColumnOf[String] )
            boardRes.foreach{ e => 
              boardName = boardName + e
              }
        }
        
        var topicRes = db.withSession{
          implicit session => 
            topics.filter(x => x.catId === cat_id).list
        }
        topicRes.foreach{n =>
          topicSeq = topicSeq  ++ {
               <tr class="topic" title={""+n.topicId+""} >
               <td class="topic-title"><a href="#">{""+n.topicSubject+""}</a></td>
               <td class="topic-start-date">{""+n.topicDate+""}</td>
                
								{
								  var postRes = db.withSession{
								    implicit session =>
								      posts.filter(x=> x.topicId === n.topicId).list
								  }
								  var userRes =   db.withSession{
                    implicit session =>
                      users.filter(x => x.userid === {postRes.last._5 }).list
                    }
								  <td class="topic-replies">{""+postRes.length+""}</td> 
                  <td class="topic-views">TO-DO</td>
                  <td class="topic-last">{""+userRes.last._2+""}</td>
                  
								}
							</tr> 
          }
        }
        
    
    <html>
    <head lang="en">
       <meta charset="utf-8"/>
        <title>Forum</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
				<link href='http://fonts.googleapis.com/css?family=Monoton' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Cutive+Mono' rel='stylesheet' type='text/css'/>
        <link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'/>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" tpye="text/css" href="css/forum-styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/about-styles.css"/>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script type="text/javascript" src="js/setUpNavigation-forum.js"></script>
        <script type="text/javascript" src="js/categories.js"></script>
        <script type="text/javascript" src="js/inner-matrix.js"></script>
    </head>
		<body>
    	<div id="navBar"></div>
			<div id="mainContainer">
    		<div class="subtitle">{""+catSplit(1)+"" }</div>
    		<ul class="breadcrumb">
        	<li><a href="/forum">Forum</a></li>
        	<li><a>Category: {boardName}</a></li>
    		</ul>
  <!--  
    EchoForm("category.php","pagesForm",$pagenames,$pagevals);
	-->
    <table id="forum-2-table">
        <tr>
            <th class="topic-title">Title</th>
            <th class="topic-start-date">Start Date</th>
            <th class="topic-replies">Replies</th>
            <th class="topic-views">Views</th>
            <th class="topic-last">Last Message</th>
        </tr>
        {for(n <- topicSeq) 
			     yield n}
    </table> 
    <form method="post" id="toTopic" action="/posts">
        {echoHiddenInput("topic_id", "-1")}
        {//echoHiddenInput("page",1)
        }
    </form>
     
    <canvas id="c" z-index="-2"></canvas>

   </div>
  </body>
 </html>
 }
    
  def echoHiddenInput(name:String, inp:String) = {
      var ret:scala.xml.Node = {
        <input id={""+name+""} type="hidden" style="display:none" value={""+inp+""} name={""+name+""}></input>
      } 
      ret
    }

  def myG(s:Option[String]) ={
     s match {
         case Some(n) => n
         case None => "-1"
      }
    }

}