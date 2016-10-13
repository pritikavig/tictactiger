package models


/*
  Specifies the location of the move and the player attempting to move
 */

case class Move ( square: Int,
                  playerId: String )