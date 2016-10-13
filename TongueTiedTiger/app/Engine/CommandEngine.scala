package Engine
import javax.inject._
import scala.util.Try

/*
  Class to sort the command arguments specified with /ttt
 */

@Singleton
class CommandEngine @Inject() (ttt: TicTacToeEngine) {
  val moveRegex = """move (?<!\S)\d(?!\S)""".r

  def sortCommands(text: String, channelName: String, username: String): String = {

    (text, text(0)) match {

      // case game display game
      case ("game", _) => ttt.showBoard(channelName).getOrElse("Board not found")

      // case class to print help message
      case ("help", _) => ttt.help()

      // end game
      case ("gameover", _) => ttt.gameOver(channelName) match { case true => "Game ended successfully."; case false => "Game not found." }

      // case move <int> make move
      case (moveRegex(), 'm' ) => Try(text.split(" ")(1).toInt).toOption match { case Some(d) if d < 8 =>
        ttt.attemptMove(channelName, username, d) match { case true => ttt.showBoard(channelName).getOrElse("Error displaying board"),
          case false => "Invalid move, try again." }
      case None => "Did not specify a digit to represent a space in the board. /ttt help for directions." }

      // case @user start game
      case (_, '@') => ttt.createGame(username, text.stripPrefix("@"), channelName) match {
        case true => "Game created!\\n" +
          ttt.showBoard(channelName).getOrElse("Error displaying board")
        case false => "Game already exists in channel."
      }

      // case default = invalid command
      case (_, _) => "Invalid arguments. For help, type /ttt help"

    }
  }

}
