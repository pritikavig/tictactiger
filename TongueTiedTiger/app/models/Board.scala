package models

import java.util.UUID

/*
 Generates a board to hold the state of a game
 */
case class Board( boardId: UUID,
                  players: Array[Player],
                  state: Array[Int],
                  turnIsPlayer1: Boolean )
//                         ) {
//
//  /*
//    Returns a string with a message to prompt the next desired action
//   */
//  def communicateStatus: String
//
//  /*
//    Attempts to make a move on the board - returns true if made successfully
//    Toggles the element of state that tracks turns (turnIsPlayer1)
//   */
//  def makeMove(move: Move): Boolean
//
//
//  /*
//    Returns the Player current turn
//   */
//  def whoseTurn: Player
//
//  /*
//   Checks to see if the game is a Draw - Boolean
//   */
//  def isDraw: Boolean
//
//  /*
//    Checks to see if the game is a win - Boolean
//  */
//  def isWinning: Boolean
//
//}
//

