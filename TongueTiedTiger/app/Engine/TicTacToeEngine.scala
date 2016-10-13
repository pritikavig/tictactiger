package Engine


trait TicTacToeEngine {


  /*
    Get instructions on how to play the game
   */
  def help(): String

  /*
  If a game doesn't already exist in the channel, create one!
 */
  def createGame(username1: String, username2: String, channelName: String): Boolean

  /*
    Get the string representation of the board for a channel
   */
  def showBoard(channelName: String): Option[String]

  /*
    Attempt to make a move
   */
  def attemptMove(channelName: String, username: String, squareNo: Int): Boolean

  /*
    Clear the current game
   */
  def gameOver(channelName: String): Boolean

  /*
    Return true if game is over and no winner
   */

  def isWon(channelName: String): Boolean

  /*
    Return true if winning board
   */
  def isOver(channelName: String): Boolean

}
