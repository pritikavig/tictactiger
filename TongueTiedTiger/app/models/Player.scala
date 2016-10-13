package models


/*
  Represents a player
  @param playerId is the username of the player from Slack
  @param playerType is the character (X or O) that represents the player in the game
 */
case class Player( playerId: String,
                   playerType: Char )