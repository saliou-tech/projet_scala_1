package services

import com.google.inject.Inject
import models.{Matiere, MatiereList}
 
import scala.concurrent.Future
import models.Matiere
class MatiereService @Inject() (items: MatiereList){

def addItem(matiere: Matiere): Future[String] = {
    items.add(matiere)
  }

}