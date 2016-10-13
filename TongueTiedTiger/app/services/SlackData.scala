package services
import play.api.mvc._


/*
  Class to hold incoming data from POST
 */

class SlackData(request: Request[AnyContent]) {
  val pritikasToken: String = "AS9u0jQ9Wdbw5ECnJbb82xZQ"
  val slackroomToken: String = "23XvOnpvpaf0dFUXpIlo4XnO"

  val token: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("token").flatMap(_.headOption))
  val teamId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("team_id").flatMap(_.headOption))
  val teamDomain: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("team_domain").flatMap(_.headOption))
  val channelId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("channel_id").flatMap(_.headOption))
  val channelName: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("channel_name").flatMap(_.headOption))
  val userId: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("user_id").flatMap(_.headOption))
  val username: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("user_name").flatMap(_.headOption))
  val command: Option[String] = request.body.asFormUrlEncoded.flatMap(m => m.get("text").flatMap(_.headOption))


  def isValidToken: Boolean = {
     token match { case Some(t) => t == pritikasToken | t == slackroomToken; case _ => false }
  }

}
