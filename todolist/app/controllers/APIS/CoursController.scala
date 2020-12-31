package controllers.APIS
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Cours, CoursForm}
import play.api.data.FormError
 
import services.CoursService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Cours}

class CoursController @Inject()(
cc: ControllerComponents,
coursService: CoursService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Cours]
    def getAll = Action {
      
        val todo = new Cours(1, "10-10-2020", "10h-12h",1,1,1,1)
        Ok(Json.toJson(todo))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        CoursForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newCoursItem = Cours(
              0,
             data.date_cours, 
             data.heure_cours,
             data.id_classe,
             data.id_matiere,
             data.id_prof,
             data.id_salle


            )
            coursService.addItem(newCoursItem).map( _ => Redirect(routes.CoursController.getAll))
          })
      }
}
 