package datastore
import scala.collection.mutable._
import models.Board
import java.util.UUID



/*
  The GameDAO mock impl stores the current boards in memory - will be wiped when server is restarted
 */

object GameDAOMockImpl extends GameDAO {

  /* Static hash table as in memory controllers.datastore */
  private val GameDataStore: HashMap[UUID,Board] = HashMap[UUID,Board]()

  /* check to see if board is already in datastore */
  def containsBoard(boardId: UUID): Boolean ={
    GameDataStore.contains(boardId)
  }

  /* if no board exists for current channel, insert & return true */
  def insertBoard(board: Board): Boolean = {
    def successfulInsert(b: Option[Board]): Boolean = b map(_=> false) getOrElse true
    successfulInsert(GameDataStore put (board.boardId, board))
  }

  /* update the current boards state  */
  def saveBoard(board: Board): Option[Board] = {
    GameDataStore put (board.boardId, board)
  }

  /* get the current board from memory */
  def getBoard(boardId: UUID): Option[Board] = {
    GameDataStore.get(boardId)
  }

}
