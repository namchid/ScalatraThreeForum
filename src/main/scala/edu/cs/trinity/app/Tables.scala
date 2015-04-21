package edu.cs.trinity.app

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.GetResult

class Post(tag: Tag) extends Table[(Int, String, Timestamp, Int, Int)](tag, "posts") {
  def postId = column[Int]("post_id", O.PrimaryKey, O.AutoInc)
  def postContent = column[String]("post_content")
  def postDate = column[Timestamp]("post_date")
  def topicId = column[Int]("topic_id")
  def userId = column[Int]("user_id")

  def * = (postId, postContent, postDate, topicId, userId)
}

case class TopicCaseClass (
  topicId: Int,
  topicSubject: String,
  topicDate: Timestamp,
  catId: Int,
  userId: Int
)

class Topic(tag: Tag) extends Table[TopicCaseClass](tag, "topics") {
  def topicId = column[Int]("topic_id", O.PrimaryKey, O.AutoInc)
  def topicSubject = column[String]("topic_subject")
  def topicDate = column[Timestamp]("topic_date")
  def catId = column[Int]("cat_id")
  def userId = column[Int]("user_id")

  def * = (topicId, topicSubject, topicDate, catId, userId) <> ((TopicCaseClass.apply _).tupled, (TopicCaseClass).unapply) 
}

object Tables {
  val users = TableQuery[User]
  val posts = TableQuery[Post]
  val topics = TableQuery[Topic]
  val topicsMap = topics.map(t => (t.topicId -> t.topicSubject)) 
  
  implicit val getUser2 = GetResult(r => User2(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
}