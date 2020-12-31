
package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._



case class Cours(
  id: Long,
  date_cours:String,
  heure_cours:String,
  id_prof:Long,
  id_classe:Long,
  id_salle:Long,
  id_matiere:Long
)
case class CoursFormData(
  date_cours:String,
  heure_cours:String,
  id_prof:Long,
  id_classe:Long,
  id_salle:Long,
  id_matiere:Long

)
object CoursForm {
  val form = Form(
    mapping(
      "date_cours" -> nonEmptyText,
      "heure_cours" -> nonEmptyText,
      "id_prof" -> longNumber,
      "id_classe" -> longNumber,
      "id_salle" -> longNumber,
      "id_matiere" -> longNumber,
    )(CoursFormData.apply)(CoursFormData.unapply)
  )
}

class CoursTableDef(tag: Tag) extends Table[Cours](tag, "cours") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def date_cours = column[String]("date_cours")
  def heure_cours = column[String]("heure_cours")
  def id_prof =column[Long]("id_prof")
def id_classe =column[Long]("id_classe")
  def id_salle =column[Long]("id_salle")
 def id_matiere =column[Long]("id_matiere")

  def * = (id, date_cours, heure_cours,id_prof,id_classe,id_salle,id_matiere) <> ((Cours.apply _).tupled, Cours.unapply _)

  def classefk = foreignKey("classe",id_classe,TableQuery[ClasseTableDef])(_.id)
  def proffk = foreignKey("professeur",id_prof,TableQuery[ProfesseurTableDef])(_.id)
def sallefk = foreignKey("salle",id_prof,TableQuery[SalleTableDef])(_.id)
def matierefk = foreignKey("matiere",id_matiere,TableQuery[MatiereTableDef])(_.id)
}
class CoursList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var CoursList = TableQuery[CoursTableDef]
  def add(coursItem: Cours): Future[String] = {
    dbConfig.db
      .run(CoursList += coursItem)
      .map(res => "cours successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}
