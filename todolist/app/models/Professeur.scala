package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Professeur(
  id: Long,
  nomProfesseur: String,
  prenomProfesseur: String,
  grade:String
  
)
case class ProfesseurFormData(
  nomProfesseur: String,
  prenomProfesseur: String,
  grade:String
  
)
object ProfesseurForm {
  val form = Form(
    mapping(
      "nomProfesseur" -> nonEmptyText,
      "prenomProfesseur" -> nonEmptyText,
      "grade" -> nonEmptyText


    )(ProfesseurFormData.apply)(ProfesseurFormData.unapply)
  )
}
class ProfesseurTableDef(tag: Tag) extends Table[Professeur](tag, "professeur") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def nomProfesseur = column[String]("nomProfesseur")
  def prenomProfesseur = column[String]("prenomProfesseur")
  def grade =column[String]("grade")
 
  def * = (id, nomProfesseur, prenomProfesseur,grade) <> ((Professeur.apply _).tupled, Professeur.unapply _)
}

class ProfesseurList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var ProfesseurList = TableQuery[ProfesseurTableDef]
  def add(professeurItem: Professeur): Future[String] = {
    dbConfig.db
      .run(ProfesseurList += professeurItem)
      .map(res => "Professeur successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}
