package services

import com.google.inject.Inject
import models.{Evaluation, EvaluationList}
 
import scala.concurrent.Future
class EvaluationService @Inject() (items: EvaluationList){

def addItem(evaluation: Evaluation): Future[String] = {
    items.add(evaluation)
  }

}