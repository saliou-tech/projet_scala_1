package services


import com.google.inject.Inject
import models.{Cours, CoursList}
 
import scala.concurrent.Future
class CoursService @Inject() (items: CoursList){

def addItem(coursitem: Cours): Future[String] = {
    items.add(coursitem)
  }

}