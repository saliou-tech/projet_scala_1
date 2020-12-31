package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Etudiant(
  id: Long,
  nomEtudiant: String,
  prenomEtudiant: String,
  niveauEtude:String,
  id_classe:Long
  
)
case class EtudiantFormData(
  nomEtudiant: String,
  prenomEtudiant: String,
  niveauEtude:String,
  id_classe:Long

)
object EtudiantForm {
  val form = Form(
    mapping(
      "nomEtudiant" -> nonEmptyText,
      "prenomEtudiant" -> nonEmptyText,
      "niveauEtude" -> nonEmptyText,
      "id_classe" -> longNumber,
    )(EtudiantFormData.apply)(EtudiantFormData.unapply)
  )
}
class EtudiantTableDef(tag: Tag) extends Table[Etudiant](tag, "etudiant") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def nomEtudiant = column[String]("nomEtudiant")
  def prenomEtudiant = column[String]("prenomEtudiant")
  def niveauEtude =column[String]("niveauETude")
  def id_classe =column[Long]("id_classe")
  def * = (id, nomEtudiant, prenomEtudiant,niveauEtude,id_classe) <> ((Etudiant.apply _).tupled, Etudiant.unapply _)
  def classefk = foreignKey("classe",id_classe,TableQuery[ClasseTableDef])(_.id)

}

class EtudiantList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var EtudiantList = TableQuery[EtudiantTableDef]
  def add(etudiantItem: Etudiant): Future[String] = {
    dbConfig.db
      .run(EtudiantList += etudiantItem)
      .map(res => "Etudiant successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}