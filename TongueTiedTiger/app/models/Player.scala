package models

import java.util.UUID

/*
  Represents a player
 */
case class Player( playerId: UUID,
                   playerType: Char )