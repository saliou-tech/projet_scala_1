package services

import com.google.inject.Inject
import models.{Classe,ClasseList}
 
import scala.concurrent.Future
class ClasseService @Inject() (items: ClasseList){

def addItem(classe: Classe): Future[String] = {
    items.add(classe)
  }

}