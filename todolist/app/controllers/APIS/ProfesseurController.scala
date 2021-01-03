package controllers.APIS

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Professeur, ProfesseurForm}
import play.api.data.FormError
 
import services.ProfesseurService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Professeur}

class ProfesseurController @Inject()(
cc: ControllerComponents,
professeurService: ProfesseurService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Professeur]
   def getAll() = Action.async { implicit request: Request[AnyContent] =>
        professeurService.listAllItems map { items =>
          Ok(Json.toJson(items))
        }
      }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        ProfesseurForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newProfesseurItem = Professeur(0, data.nomProfesseur, data.prenomProfesseur,data.grade)
            professeurService.addItem(newProfesseurItem).map( _ => Redirect(routes.ProfesseurController.getAll))
          })
      }
}
