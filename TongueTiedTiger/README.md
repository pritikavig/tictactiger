# Documentation 

See the top-level readme for more information on running and deploying the application.

## Tools
The application was developed in Scala using Play Framework. I learned Scala this summer at my internship and I am most familiar with deployments through Play Framework - I'm rusty on beautifully idiomatic Scala as my current labs are in C, but I had a blast making this integration! Definitely my favorite interview so far!

The documentation and source code are publicly available at https://github.com/pritikavig/tictactiger. The app is deployed through Heroku at https://boiling-fortress-23577.herokuapp.com/. The team through which the integration runs is https://ae30957667test0.slack.com/messages/general/. 

To run my tests, clone the app, cd into the root directory of the project and run ```sbt test```. All tests will execute. 

## Model Representation
I chose to represent the state of a game with the Board class. A Board has two Players, an identification string, which in practice is the channel id, an array of size 9 with a location for each space on the board and a Boolean value to represent if it is player ones turn. The array holds the current state of the board, with a character ' ' representing an available space and 'X' or 'O' representing moves.
```scala
case class Board( boardId: String,
                  players: Array[Player],
                  var state: Array[Char] = Array.fill(9)(' '),
                  var turnIsPlayer1: Boolean = true
                )
```
A Player has a player identification and a character associated with their move ('X' or 'O'). For simplicity in the data being returned from the POST, I used the user name of the player as the player identification. In this way, when a player initiates a game I can immediately construct Player objects without needing to wait for another request for the second players user id. If i were to scale this app, i could has the team id and user name together to create a UUID for a player, but as players are associated with games and games with channels, we are guaranteed to avoid collisions as no two users in a team may have the same user name. 
```scala
case class Player( playerId: String,
                   playerType: Char )
```
A Move is represented with a player identification and an integer location on the board. This board can be viewed with the ```/ttt help``` command.
```scala
case class Move ( square: Int,
                  playerId: String )
```
## Actions to update state
I created a Game trait and a TicTacToeEngine that act as a layer between a call for an action and the Board case class. The Board implements methods of the trait to mutate state. 
```scala
trait Game {
  def whoseTurn(): Player
  def makeMove(move: Move): Boolean
  def isWinningBoard(): Boolean
  def isFull(): Boolean
}
```
The TicTacToeEngine interfaces between the controller POST request data and calling the Game methods to mutate the object and then updating the persistence with the game data access object (GameDAO). 
```scala
trait TicTacToeEngine {
  def help(): String
  def createGame(username1: String, username2: String,
                channelName: String): Boolean
  def showBoard(channelName: String): Option[String]
  def attemptMove(channelName: String, username: String, 
                squareNo: Int): Boolean
  def gameOver(channelName: String): Boolean
  def isWon(channelName: String): Boolean
  def isOver(channelName: String): Boolean
}
```

## Persistence of Data 

As a version 0 approach, I used a hash map as an in memory data store to store each Board with its channel identification as the key. This had the benefit of constant time look up and insertion. However, games will not persist once the instance of the server terminates and if we were to deploy on multiple instances the game would not work. In a future version, a persistent database like PostGresSQL would allow shared storage between instance and persistence after termination. As a more effective storage mechanism (games in channels are now getting replaced after they finish) it could be really cool to store past games and show a users statistics upon a command. I used dependency injection to bind my implementations to the traits. Thus adding an implementation of the data access object with a persistent storage will be simple to refactor. 
    
Some additional future aspects I would like to implement would be Errors and logging. I haven't had a lot of experience with it at school and I am currently investigating methods. Additionally, I implemented a gameover method so that a user can stop the game, because I didn't validate the co-player. Validation and logging would be my v1 priorities. 

## Thanks very much!