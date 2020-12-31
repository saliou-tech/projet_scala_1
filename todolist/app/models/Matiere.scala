package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Matiere(
  id: Long,
  codeMatiere: String,
  libelleMatiere: String,
  coeffMatiere:Long
  
)
case class MatiereFormData(
  codeMatiere: String,
  libelleMatiere: String,
  coeffMatiere:Long  
)
object MatiereForm {
  val form = Form(
    mapping(
      "codeMatiere" -> nonEmptyText,
      "libelleMatiere" -> nonEmptyText,
      "coeffMatiere" ->  longNumber

    )(MatiereFormData.apply)(MatiereFormData.unapply)
  )
}
class MatiereTableDef(tag: Tag) extends Table[Matiere](tag, "matiere") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def codeMatiere = column[String]("codeMatiere")
  def libelleMatiere = column[String]("libelleMatiere")
  def coeffMatiere =column[Long]("coeffMatiere")
 
  def * = (id, codeMatiere, libelleMatiere,coeffMatiere) <> ((Matiere.apply _).tupled, Matiere.unapply _)
}

class MatiereList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var MatiereList = TableQuery[MatiereTableDef]
  def add(matiereItem: Matiere): Future[String] = {
    dbConfig.db
      .run(MatiereList += matiereItem)
      .map(res => "Matiere successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}
