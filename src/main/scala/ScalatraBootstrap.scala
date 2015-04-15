import edu.cs.trinity.app._
import org.scalatra._
import javax.servlet.ServletContext
import scala.slick.driver.MySQLDriver.simple._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val db = Database.forURL("jdbc:mysql://localhost/bpauls", user="bpauls", password="0742985", driver="com.mysql.jdbc.Driver")
    println("The DB is "+db)
    context.mount(new ThreeForumServlet(db), "/*")
  }
}
