
import models.{Player, Board}
import org.scalatest._
import java.util.UUID
import datastore.GameDAOMockImpl._


class ModelsTest extends FlatSpec with Matchers {

  val player1Id = "pritika"
  val player1: Player = Player(player1Id, 'X')
  val player2: Player = Player("lindsey", 'O')
  val players = Array( player1, player2 )
  val boardId = "boardchannel"
  val board: Board = Board(boardId, players)

  "A Player" should "have an id field" in {

    player1.playerId should be (player1Id)
  }

  "A Board" should "initialize empty" in {
    board.state(0) should be(' ')
    board.state(8) should be(' ')
  }

  "A DAO" should "insert a new value and check to see if contains" in {
    insertBoard(board) should be(true)
    containsBoard(boardId) should be(true)
  }

  "A DAO" should "return the correct board in ds" in {
    val b2 = Board("chan", players)
    insertBoard(b2)
    getBoard(boardId) should be (Some(board))
  }

}

