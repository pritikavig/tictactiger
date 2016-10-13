
import models.{Player, Board}
import org.scalatest._
import java.util.UUID
import datastore.GameDAOMockImpl._


class ModelsTest extends FlatSpec with Matchers {

  val player1Id = UUID.randomUUID
  val player1: Player = new Player(player1Id, 2)
  val player2: Player = new Player(UUID.randomUUID, 3)
  val players = Array( player1, player2 )
  val state: Array[Int] = Array.fill(9)(0)
  val boardId = UUID.randomUUID()
  val board: Board = new Board(boardId, players, state, true)

  "A Player" should "have an id field" in {

    player1.playerId should be (player1Id)
  }

  "A Board" should "initialize empty" in {
    state(0) should be(0)
    state(8) should be(0)
  }

  "A DAO" should "insert a new value and check to see if contains" in {
    insertBoard(board) should be(true)
    containsBoard(boardId) should be(true)
  }

  "A DAO" should "return the correct board in ds" in {
    val b2 = new Board(UUID.randomUUID, players, state, false)
    insertBoard(b2)
    getBoard(boardId) should be (Some(board))
  }

}

