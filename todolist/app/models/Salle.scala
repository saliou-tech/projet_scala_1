
package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Salle(
  id: Long,
  numeroSalle: String,
  libelleSalle: String,
)
case class SalleFormData(
  numeroSalle: String,
  libelleSalle:String
  
)
object SalleForm {
  val form = Form(
    mapping(
      "numeroSalle" -> nonEmptyText,
      "libelleSalle" -> nonEmptyText
    )(SalleFormData.apply)(SalleFormData.unapply)
  )
}
class SalleTableDef(tag: Tag) extends Table[Salle](tag, "salle") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def numeroSalle = column[String]("numeroSalle")
  def libelleSalle = column[String]("libelleSalle") 
  def * = (id, numeroSalle, libelleSalle) <> ((Salle.apply _).tupled, Salle.unapply _)
}

class SalleList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var SalleList = TableQuery[SalleTableDef]
  def add(salleItem: Salle): Future[String] = {
    dbConfig.db
      .run(SalleList += salleItem)
      .map(res => "Salle successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}
