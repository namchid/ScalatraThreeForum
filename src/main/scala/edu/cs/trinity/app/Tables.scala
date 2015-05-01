package edu.cs.trinity.app

import java.sql.Timestamp

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.GetResult
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }

case class TopicCaseClass(
  topicId: Int,
  topicSubject: String,
  topicDate: Timestamp,
  catId: Int,
  userId: Int)

case class PostCaseClass(
  postId: Int,
  postContent: String,
  postDate: Timestamp,
  topicId: Int,
  userId: Int)

case class UserCaseClass(
  userId: Int,
  username: String,
  userPass: String,
  userEmail: String,
  userDate: Timestamp,
  userLevel: Int)

case class BoardCaseClass(
  boardId: Int,
  boardName: String,
  boardDescription: String)

case class CategoryCaseClass(
  catId: Int,
  catName: String,
  catDescription: String,
  boardId: Int)

class Post(tag: Tag) extends Table[PostCaseClass](tag, "posts") {
  def postId = column[Int]("post_id", O.PrimaryKey, O.AutoInc)
  def postContent = column[String]("post_content")
  def postDate = column[Timestamp]("post_date")
  def topicId = column[Int]("topic_id")
  def userId = column[Int]("user_id")

  def * = (postId, postContent, postDate, topicId, userId) <> ((PostCaseClass.apply _).tupled, (PostCaseClass.unapply))
}

class Topic(tag: Tag) extends Table[TopicCaseClass](tag, "topics") {
  def topicId = column[Int]("topic_id", O.PrimaryKey, O.AutoInc)
  def topicSubject = column[String]("topic_subject")
  def topicDate = column[Timestamp]("topic_date")
  def catId = column[Int]("cat_id")
  def userId = column[Int]("user_id")

  def * = (topicId, topicSubject, topicDate, catId, userId) <> ((TopicCaseClass.apply _).tupled, (TopicCaseClass).unapply)
}

class User(tag: Tag) extends Table[UserCaseClass](tag, "users") {
  def userId = column[Int]("user_id", O.PrimaryKey, O.AutoInc)
  def username = column[String]("user_name")
  def userPass = column[String]("user_pass")
  def userEmail = column[String]("user_email")
  def userDate = column[Timestamp]("user_date")
  def userLevel = column[Int]("user_level")

  def * = (userId, username, userPass, userEmail, userDate, userLevel) <> ((UserCaseClass.apply _).tupled, (UserCaseClass).unapply)
}

class Board(tag: Tag) extends Table[BoardCaseClass](tag, "boards") {
  def boardId = column[Int]("board_id", O.PrimaryKey, O.AutoInc)
  def boardName = column[String]("board_name")
  def boardDescription = column[String]("board_description")

  def * = (boardId, boardName, boardDescription) <> ((BoardCaseClass.apply _).tupled, (BoardCaseClass).unapply)
}

class Category(tag: Tag) extends Table[CategoryCaseClass](tag, "categories") {
  def catId = column[Int]("cat_id", O.PrimaryKey, O.AutoInc)
  def catName = column[String]("cat_name")
  def catDescription = column[String]("cat_description")
  def boardId = column[Int]("board_id")

  def * = (catId, catName, catDescription, boardId) <> ((CategoryCaseClass.apply _).tupled, (CategoryCaseClass).unapply)
}

object Tables {
  val users = TableQuery[User]
  val posts = TableQuery[Post]
  val topics = TableQuery[Topic]
  val boards = TableQuery[Board]
  val categories = TableQuery[Category]

  val topicsMap = topics.map(t => (t.topicId -> t.topicSubject))

}