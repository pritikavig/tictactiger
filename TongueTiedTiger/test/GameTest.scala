import models.{Player, Board, Move}
import org.scalatest._


class GameTest extends FlatSpec with Matchers {

  val player1Id = "pritika"
  val player2Id = "lindsey"
  val player1: Player = Player(player1Id, 'X')
  val player2: Player = Player(player2Id, 'O')
  val players = Array( player1, player2 )
  val boardId = "channel"
  val board: Board = Board(boardId, players)

  "A Game" should "state whose turn it is" in {
    board.whoseTurn() should be(board.players(0))
  }

  "A Game" should "make a move" in {
    val move = Move(1, player1Id)
    board.makeMove(move) should be(true)
    board.state(1) should be ('X')
  }

  "A Game" should "not allow an illegal player to move" in {

    val move = Move(2, player1Id)
    board.whoseTurn() should be(players(1))
    board.makeMove(move) should be(false)
  }

  "A Game" should "not allow an illegal move" in {
    val move1 = Move(1, board.whoseTurn().playerId)
    board.makeMove(move1) should be(false)
  }

  "A Game" should "alternate players" in {
    val move1 = Move(3, players(1).playerId)
    board.makeMove(move1)
    val move2 = Move(5, players(0).playerId)
    board.makeMove(move2)
    val move3 = Move(8, players(1).playerId)
    board.makeMove(move3)

    board.state(3) should be('O')
    board.state(5) should be('X')
    board.state should be(Array(' ', 'X', ' ', 'O', ' ', 'X', ' ', ' ', 'O'))
    board.state(8) should be('O')

  }

  "A Game" should "detect a winning board" in {
    val board1: Board = Board(boardId, players)
    val board2: Board = Board(boardId, players)
    val board3: Board = Board(boardId, players)
    board1.state = Array('X', 'X', 'X', ' ', 'O', 'O', 'X', 'O', 'X')
    board2.state = Array('X', '0', 'O', ' ', 'X', 'O', 'X', 'O', 'X')
    board3.state = Array('O', 'X', 'O', 'O', 'X', 'O', 'O', 'O', ' ')

    board.isWinning() should be(false)
    board1.isWinning() should be(true)
    board2.isWinning() should be(true)
    board3.isWinning() should be(true)
  }

  "A Game" should "know when full" in {
    val board1: Board = Board(boardId, players)
    val board3: Board = Board(boardId, players)
    board1.state = Array('X', 'X', 'X', ' ', 'O', 'O', 'X', 'O', 'X')
    board3.state = Array('O', 'X', 'O', 'O', 'X', 'O', 'O', 'O', 'X')

    board.isFull() should be(false)
    board1.isFull() should be(false)
    board3.isFull() should be(true)
  }

}