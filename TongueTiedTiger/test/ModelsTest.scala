import datastore.GameDAOMockImpl
import models.{Board, Player}
import org.scalatest._



class ModelsTest extends FlatSpec with Matchers {

  val gd = new GameDAOMockImpl()
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
    gd.insertBoard(board)
    gd.containsBoard(boardId) should be(true)
  }

  "A DAO" should "return the correct board in ds" in {
    val b2 = Board("chan", players)
    gd.insertBoard(b2)
    gd.getBoard(boardId) should be (Some(board))
  }

  "A DAO" should "remove a board" in {
    gd.removeGame(boardId) should be(true)
    gd.removeGame("aslfjasdfa") should be(false)
  }

}

