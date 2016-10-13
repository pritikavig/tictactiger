package controllers

import javax.inject._
import play.api.mvc._

/*

  Supported actions:

  /ttt @player <- if a game doesn't already exist in the channel, create one

  /ttt game <- show current game & turn in channel

  /ttt gameover <- clear current game in channel

  /ttt move <move> <- attempt to make a move for the player


 */

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action { request =>

    /* --- Hacky Slack Client -- my apologies ---- */
    val token: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("token").flatMap(_.headOption))
    val teamId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("team_id").flatMap(_.headOption))
    val teamDomain: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("team_domain").flatMap(_.headOption))
    val channelId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("channel_id").flatMap(_.headOption))
    val channelName: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("channel_name").flatMap(_.headOption))
    val userId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("user_id").flatMap(_.headOption))
    val username: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("user_name").flatMap(_.headOption))
    val command: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("text").flatMap(_.headOption))

    Ok(request.body.toString)

  }

}


object Command extends Enumeration {

}