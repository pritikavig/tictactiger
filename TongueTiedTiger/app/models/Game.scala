package models

/*
  Actions that a Game must implement - extended by Board
 */

trait Game {

  def whoseTurn(): Player

  def makeMove(move: Move): Boolean

  def isWinningBoard(): Boolean

  def isFull(): Boolean

}
