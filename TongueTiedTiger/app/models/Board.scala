package models


/*
 Generates a board to hold the state of a game
 */
case class Board( boardId: String,
                  players: Array[Player],
                  var state: Array[Char] = Array.fill(9)(' '),
                  var turnIsPlayer1: Boolean = true
                ) extends Game {



   // Attempts to make a move on the board - returns true if made successfully
   // Private func toggles the element of state that tracks turns (turnIsPlayer1) if move made
  def makeMove(move: Move): Boolean = {
    def finishedTurn() = { turnIsPlayer1 = !turnIsPlayer1 }
    def moveSquare: Boolean = { state(move.square) = whoseTurn().playerType; finishedTurn(); true }

    if ( move.playerId != whoseTurn().playerId ) { return false }

    state(move.square) match {
      case ' ' => moveSquare
      case _ => false
    }

  }


   // Returns the Player current turn
   def whoseTurn(): Player = {
     if (turnIsPlayer1) players(0) else players(1)
   }



  // Checks to see if the board is a full - Boolean
  def isFull(): Boolean = {
    !state.contains(' ')
  }


  //  Checks to see if the game is a win - Boolean
  def isWinningBoard(): Boolean = {
    def isSame(x: Array[Char]): Boolean = x(0) == x(1) & x(0) == x(2) & x(0) != ' '
    def checkWin(l: List[Array[Char]]): Boolean = l.map { x => isSame(x) }.contains(true)
    def rowWin = checkWin( state.sliding(3).toList )
    def colWin = checkWin( List( Array( state(0), state(3), state(6) ), Array(state(1), state(4), state(7)),
      Array(state(2), state(5), state(8))) )
    def diagWin = checkWin( List( Array( state(0), state(4), state(8) ), Array(state(2), state(4), state(6))) )


    List(rowWin, colWin, diagWin).contains(true)


  }



  // Prints the state of the board
  def prettyPrint(): String = {
    val curPlayer = if (turnIsPlayer1) players(0).playerId else players(1).playerId
    val message = if (!isWinningBoard() & !isFull()) s"\\n It's $curPlayer's turn to move!\\n" else " "

    """```|""" + s" ${state(0)} | ${state(1)} | ${state(2)} |\\n|---+---+---|\\n| ${state(3)} | ${state(4)} | ${state(5)} |\\n|---+---+---|\\n| ${state(6)} | ${state(7)} | ${state(8)} " + """|```""" +
    message

  }

}


