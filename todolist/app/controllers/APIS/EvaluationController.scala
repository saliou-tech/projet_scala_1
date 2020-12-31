package controllers.APIS
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Evaluation, EvaluationForm}
import play.api.data.FormError
 
import services.EvaluationService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Evaluation}

class EvaluationController @Inject()(
cc: ControllerComponents,
evaluationService: EvaluationService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Evaluation]
    def getAll = Action {
      
        val todo = new Evaluation(1, 12, 13,15,1,1)
        Ok(Json.toJson(todo))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        EvaluationForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newEvaluationItem = Evaluation(
                0, 
            data.devoir1,
             data.devoir2,
             data.examen,
             data.id_etudiant,
             data.id_matiere)
            evaluationService.addItem(newEvaluationItem).map( _ => Redirect(routes.EvaluationController.getAll))
          })
      }
}