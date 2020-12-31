package controllers.APIS
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Salle, SalleForm}
import play.api.data.FormError
 
import services.SalleService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{Salle}
class SalleController @Inject()(
cc: ControllerComponents,
salleService: SalleService
) extends AbstractController(cc){
    
    implicit val todoFormat = Json.format[Salle]
    def getAll = Action {
      
        val salle = new Salle(1, "Cices", "Salla Master Big Data &IA" )
        Ok(Json.toJson(salle))
    }
    def add() = Action.async { implicit request: Request[AnyContent] =>
        SalleForm.form.bindFromRequest.fold(
          // if any error in submitted data
          errorForm => {
            errorForm.errors.foreach(println)
            Future.successful(BadRequest("Error!"))
          },
          data => {
            val newSalleItem = Salle(0, data.numeroSalle, data.libelleSalle)
            salleService.addItem(newSalleItem).map( _ => Redirect(routes.SalleController.getAll))
          })
      }
}