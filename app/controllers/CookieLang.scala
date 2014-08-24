package controllers

import play.api.mvc.{ Action, Controller }
import play.api.i18n.Lang
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import play.api.Play.current

object CookieLang extends Controller {

  protected val HOME_URL = "/"

  def switch(code: String) = Action {
    implicit request =>
      val referrer = request.headers.get(REFERER).getOrElse(HOME_URL)
      Logger.logger.debug("Change user lang to : " + code)
      Redirect(referrer).withLang(Lang(code))
  }
}

