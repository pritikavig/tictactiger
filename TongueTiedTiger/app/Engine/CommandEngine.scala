package Engine
import javax.inject._

@Singleton
class CommandEngine @Inject() (ttt: TicTacToeEngine) {

  def sortCommands(text: String, channelName: String): String = {

    text match {

        // case game display game
      case "game" => ttt.showBoard(channelName) match { case Some(str) => str; case None => "Board not found in channel."}

        // case help send help message
      case "help" => ttt.help()

        // end game
      case "gameover" => ttt.gameOver(channelName) match { case true => "Game ended successfully."; case false => "Game not found." }

//      // case @user start game
//      case text takeleft 1 == '@' =>
//
//      // case move <int> make move
//      case "move" =>

        // case default = invalid command
      case _ => "Invalid arguments. For help, type /ttt help"

    }
  }

}
