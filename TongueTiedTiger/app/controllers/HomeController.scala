package controllers

import Engine.TicTacToeEngine
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import Engine.CommandEngine
import services.SlackData
/*

  Supported actions:

  /ttt @player <- if a game doesn't already exist in the channel, create one

  /ttt game <- show current game & turn in channel

  /ttt gameover <- clear current game in channel

  /ttt move <move> <- attempt to make a move for the player


 */

@Singleton
class HomeController @Inject() ( ttt: TicTacToeEngine,
                                 c: CommandEngine) extends Controller {

  def sendMessage(message: String ): JsValue = {

    val sendBack = """{ "response_type": "in_channel", "text": " """ + message + """ "}"""

    Json.parse(sendBack)

  }

  def index = Action { request =>

    val slackData = new SlackData(request)
    (slackData.command, slackData.channelId) match {
      case (Some(command), Some(cid)) => Ok(sendMessage(c.sortCommands(command, cid))).as("application/json")
      case _ => BadRequest("Error sending data.")
    }
  }

}


