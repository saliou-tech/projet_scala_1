package services
import com.google.inject.Inject
import models.{Salle, SalleList}
 
import scala.concurrent.Future
import models.Salle
class SalleService @Inject() (items: SalleList){

def addItem(salle: Salle): Future[String] = {
    items.add(salle)
  }

}