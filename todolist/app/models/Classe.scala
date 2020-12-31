package models
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._


case class Classe(
  id: Long,
  libelleClasse: String
)

case class ClasseFormData(
  libelleClasse: String
)

object ClasseForm {
  val form = Form(
    mapping(
      "libelleClasse" -> nonEmptyText
    )(ClasseFormData.apply)(ClasseFormData.unapply)
  )
}

class ClasseTableDef(tag: Tag) extends Table[Classe](tag, "classe") {
 
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def libelleClasse = column[String]("nomEtudiant")
  
 
  def * = (id, libelleClasse) <> ((Classe.apply _).tupled, Classe.unapply _)
}

class ClasseList @Inject()(
    protected val dbConfigProvider: DatabaseConfigProvider
)(implicit executionContext: ExecutionContext)
    extends HasDatabaseConfigProvider[JdbcProfile] {
 
  var ClasseList = TableQuery[ClasseTableDef]
  def add(classeItem: Classe): Future[String] = {
    dbConfig.db
      .run(ClasseList += classeItem)
      .map(res => "classe successfully added")
      .recover {
        case ex: Exception => {
            printf(ex.getMessage())
            ex.getMessage
        }
      }
  }
}