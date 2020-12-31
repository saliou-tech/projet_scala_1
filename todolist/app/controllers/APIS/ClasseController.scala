package controllers.APIS

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Classe, ClasseForm}
import play.api.data.FormError
 
import services.ClasseService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Classe}

class ClasseController @Inject()(
cc: ControllerComponents,
classeService: ClasseService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Classe]
    def getAll = Action {
      
        val todo = new Classe(1, "MASTER 1 IA")
        Ok(Json.toJson(todo))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        ClasseForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newClasseItem = Classe(0, data.libelleClasse)
            classeService.addItem(newClasseItem).map( _ => Redirect(routes.EtudiantController.getAll))
          })
      }
}
 