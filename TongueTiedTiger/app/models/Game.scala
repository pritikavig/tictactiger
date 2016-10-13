package models

/*
  Actions that a Game must implement
 */

trait Game {

  def whoseTurn(): Player

  def makeMove(move: Move): Boolean

  def isWinning(): Boolean

  def isFull(): Boolean

}
