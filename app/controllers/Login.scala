package controllers

import play.api.mvc.{ Action, Controller }
import models.Solution
import java.math.BigInteger
import java.security.SecureRandom
import java.io.File
import com.google.common.reflect.ClassPath.Scanner
import java.util.Scanner

object Login extends Controller {
  val APPLICATION_NAME = "ddgatve-math"
  val CLIENT_ID = "142624243903-vflqmb5336fmenqihfs41rokt42sbrt3.apps.googleusercontent.com"
  val REDIRECT_URI = "http://www.dudajevagatve.lv/oauth2callback"
  val LOGIN_HINT = "jsmith@example.com"

  def createStateToken = Action {
    // Create a state token to prevent request forgery.
    // Store it in the session for later validation.
    implicit request =>
      val state: String = new BigInteger(130, new SecureRandom()).toString(32)
      request.session + ("state" -> state)
      // Read index.html into memory, and set the client ID,
      // token state, and application name in the HTML before serving it.
//      val scanner = new Scanner(new File("index.html"), "UTF-8")
//        .useDelimiter("\\A").next()
//        .replaceAll("[{]{2}\\s*CLIENT_ID\\s*[}]{2}", CLIENT_ID)
//        .replaceAll("[{]{2}\\s*STATE\\s*[}]{2}", state)
//        .replaceAll("[{]{2}\\s*APPLICATION_NAME\\s*[}]{2}",
//          APPLICATION_NAME);
      val solutions = Solution.findAll
      Ok(views.html.login.login(state, CLIENT_ID, REDIRECT_URI, LOGIN_HINT))

  }
} 