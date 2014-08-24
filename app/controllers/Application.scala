package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

//  def index = Action {
//    Ok(views.html.index("Hello world."))
//  }

  def index = Action {
    Redirect(routes.Products.list())
  }

}