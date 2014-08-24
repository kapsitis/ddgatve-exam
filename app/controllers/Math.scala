package controllers

import play.api.mvc.{ Action, Controller }
import models.Solution

object Math extends Controller {
  def list = Action { implicit request =>
    val solutions = Solution.findAll
    Ok(views.html.math.list(solutions))
  }
  
  def problem(id:String) = Action {
    implicit request =>
    val solutions = Solution.findAll
    Ok(views.html.math.problem(solutions,id))
  }
} 