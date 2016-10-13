package datastore
import scala.collection.mutable._
import models.Board




/*
  The GameDAO mock impl stores the current boards in memory - will be wiped when server is restarted
 */
class GameDAOMockImpl extends GameDAO {

  /* Static hash table as in memory controllers.datastore */
  private val GameDataStore: HashMap[String,Board] = HashMap[String,Board]()

  /* check to see if board is already in datastore */
  def containsBoard(boardId: String): Boolean ={
    GameDataStore.contains(boardId)
  }

  /* if no board exists for current channel, insert & return true */
  def insertBoard(board: Board): Boolean = {
    def successfulInsert(b: Option[Board]): Boolean = b match { case Some(x) => true; case None => false }
    successfulInsert(GameDataStore put (board.boardId, board))
  }

  /* update the current boards state  */
  def saveBoard(board: Board): Option[Board] = {
    GameDataStore put (board.boardId, board)
  }

  /* get the current board from memory */
  def getBoard(boardId: String): Option[Board] = {
    GameDataStore.get(boardId)
  }

  /* remove game from memory */
  def removeGame(boardId: String): Boolean = {
    GameDataStore.remove(boardId) match { case Some(x) => true; case None => false }
  }

}
