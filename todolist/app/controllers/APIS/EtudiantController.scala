package controllers.APIS

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Etudiant, EtudiantForm}
import play.api.data.FormError
 
import services.EtudiantService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Etudiant}

class EtudiantController @Inject()(
cc: ControllerComponents,
etudiantService: EtudiantService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Etudiant]
    def getAll = Action {
      
        val todo = new Etudiant(1, "Mansour", "soow","Master1",1)
        Ok(Json.toJson(todo))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        EtudiantForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newEtudiantItem = Etudiant(
              0,
             data.nomEtudiant, 
             data.prenomEtudiant,
             data.niveauEtude,
             data.id_classe
            )
            etudiantService.addItem(newEtudiantItem).map( _ => Redirect(routes.EtudiantController.getAll))
          })
      }
}
 