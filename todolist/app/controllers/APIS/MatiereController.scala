
package controllers.APIS

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Matiere, MatiereForm}
import play.api.data.FormError
 
import services.MatiereService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Matiere}

class MatiereController @Inject()(
cc: ControllerComponents,
matiereService: MatiereService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Matiere]
    def getAll = Action {
      
        val matiere = new Matiere(1, "Mansour", "soow",8)
        Ok(Json.toJson(matiere))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        MatiereForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newMatiereItem = Matiere(0, data.codeMatiere, data.libelleMatiere,data.coeffMatiere)
            matiereService.addItem(newMatiereItem).map( _ => Redirect(routes.MatiereController.getAll))
          })
      }
}
 