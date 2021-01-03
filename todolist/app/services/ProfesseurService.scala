package services
import com.google.inject.Inject
import models.{Professeur, ProfesseurList}
 
import scala.concurrent.Future
import models.Professeur
class ProfesseurService @Inject() (items: ProfesseurList){

def addItem(prof: Professeur): Future[String] = {
    items.add(prof)
  }
  def listAllItems: Future[Seq[Professeur]] = {
    items.listAll
  }

}