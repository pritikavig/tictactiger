package Engine
import datastore.GameDAO
import models.Board
import models.{Player, Move}
import javax.inject._

@Singleton
class TicTacToeEngineImpl @Inject() (gd: GameDAO) extends TicTacToeEngine {

 // Get instructions on how to play the game
  def help(): String = {
    """ If no game is currently being played in your channel, challenge a friend with ```/ttt @username``` """ +
    """To make a move, type ```/ttt move <sq>``` where sq is the number of the square you would like to place a piece on. \n""" +
    """An example move is ```/ttt move 5``` which will move into the center of the board """ +
    """game\n```| 1 | 2 | 3 |\n|---+---+---|\n| 4 | 5 | 6 |\n|---+---+---|\n| 7 | 8 | 9 |```"""

  }


  // If a game doesn't already exist in the channel, create one!
  def createGame(username1: String, username2: String, channelName: String): Boolean = {
    val player1 = Player(username1, 'X')
    val player2 = Player(username2, 'Y')
    val board = Board(channelName, Array(player1, player2))

    gd.containsBoard(board.boardId) match {
      case false => gd.insertBoard(board)
      case true => false
    }

  }

  // Get the string representation of the board for a channel
  def showBoard(channelName: String): Option[String] = {

    gd.getBoard(channelName) match {
      case Some(board) => Some(board.prettyPrint())
      case None => None
    }
  }


  //  Attempt to make a move
  def attemptMove(channelName: String, username: String, squareNo: Int): Boolean = {
    val move = Move(squareNo, username)

      gd.getBoard(channelName) match {
        case Some(board) => board.makeMove(move)
      }
  }

  // Clear the current game
  def gameOver(channelName: String): Boolean = {
    true
  }


}
