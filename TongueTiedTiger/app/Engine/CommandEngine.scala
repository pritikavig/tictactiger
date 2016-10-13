package Engine
import javax.inject._
import scala.util.Try

/*
  Class to sort the command arguments specified with /ttt
 */

@Singleton
class CommandEngine @Inject() (ttt: TicTacToeEngine) {



  def sortCommands(text: String, channelName: String, username: String): String = {
    val moveRegex = """move (?<!\S)\d(?!\S)""".r // is valid move format

    if (text.length() < 1) { return ttt.help() }

    (text, text(0)) match {

           case ("game", _) => ttt.showBoard(channelName).getOrElse("Board not found")

           case ("help", _) => ttt.help()

           case ("gameover", _) => ttt.gameOver(channelName) match {
                          case true => "Game ended successfully.";
                          case false => "Game not found." }

           case (moveRegex(), 'm') => getInt(text) match {
                          case Some(move) if move <= 8 => attemptMove(channelName, username, move)
                          case _ => "Did not specify a digit to represent a space in the board. " +
                          "/ttt help for directions." }

           case (_, '@') => ttt.createGame(username, text.stripPrefix("@"), channelName) match {
                          case true => "Game created!\\n" + ttt.showBoard(channelName).getOrElse("Error displaying board")
                          case false => "Game already exists in channel." }

           case (_, _) => "Invalid arguments. For help, type /ttt help"

    }
  }





    /*
      Helper methods to validate and execute commands
     */

    def getInt(move: String): Option[Int] = {
      Try(move.split(" ")(1).toInt).toOption
    }

    def attemptMove(channelName: String, username: String, move: Int): String = {
      ttt.attemptMove(channelName, username, move) match {
        case true => isGameOver(channelName, username)
        case false => "Invalid move.\\n" + ttt.showBoard(channelName).getOrElse("Error displaying board") }
    }

    def isGameOver(channelName: String, username: String): String = {
      var message = " "

      if (ttt.isWon(channelName)) {
        message = s"$username won!\\n" +
        ttt.showBoard(channelName).getOrElse("Error displaying board")
        ttt.gameOver(channelName)

      } else if (ttt.isOver(channelName)) {
        message = s"It's a draw.\\n" + ttt.showBoard(channelName).getOrElse("Error displaying board")
        ttt.gameOver(channelName)

      } else {
        message = ttt.showBoard(channelName).getOrElse("Error displaying board")
      }
        message
    }

}
