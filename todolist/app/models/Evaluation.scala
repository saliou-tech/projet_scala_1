package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._



case class Evaluation(
  id: Long,
  devoir1: Long,
  devoir2: Long,
  examen:Long,
  id_etudiant:Long,
  id_matiere:Long
  
)
case class EvaluationFormData(
  devoir1: Long,
  devoir2: Long,
  examen:Long,
  id_etudiant:Long,
  id_matiere:Long
)
object EvaluationForm {
  val form = Form(
    mapping(
      "devoir1" -> longNumber,
      "devoir2" -> longNumber,
     "examen"-> longNumber,
      "id_etudiant" -> longNumber,
      "id_matiere"-> longNumber
    )(EvaluationFormData.apply)(EvaluationFormData.unapply)
  )
}
class EvaluationTableDef(tag: Tag) extends Table[Evaluation](tag, "evaluation") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def devoir1 = column[Long]("devoir1")
  def devoir2 = column[Long]("devoir2")
def examen = column[Long]("examen")

  def id_etudiant =column[Long]("id_etudiant")
  def id_matiere =column[Long]("id_matiere")
  def * = (id, devoir1, devoir2,examen,id_etudiant,id_matiere) <> ((Evaluation.apply _).tupled, Evaluation.unapply _)
 def etudiantfk = foreignKey("etudiant",id_etudiant,TableQuery[EtudiantTableDef])(_.id)
def matierefk = foreignKey("matiere",id_matiere,TableQuery[MatiereTableDef])(_.id)

}
class EvaluationList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var evaluationList = TableQuery[EvaluationTableDef]
  def add(evaluationItem: Evaluation): Future[String] = {
    dbConfig.db
      .run(evaluationList += evaluationItem)
      .map(res => "Evaluation successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}