package datastore
import models.Board



trait GameDAO {

  /* check to see if board is already in datastore */
  def containsBoard(boardId: String): Boolean

  /* if no board exists for current channel, insert & return true */
  def insertBoard(board: Board): Boolean

  /* update the current board's state  */
  def saveBoard(board: Board): Option[Board]

  /* get the current board from memory */
  def getBoard(boardId: String): Option[Board]

  /* remove the current game */
  def removeGame(boardId: String): Boolean


}