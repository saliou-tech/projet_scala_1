package services

import com.google.inject.Inject
import models.{Etudiant, EtudiantList}
 
import scala.concurrent.Future
class EtudiantService @Inject() (items: EtudiantList){

def addItem(item: Etudiant): Future[String] = {
    items.add(item)
  }

}