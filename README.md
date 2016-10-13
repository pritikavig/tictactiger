# Readme


Slack command /ttt integration, to play tic tac toe!

To run locally:

  - install sbt, scala, activator, play (I developed in Intellij)
  - clone the repo and cd into the root project directory
  ```sh
$ cd TongueTiedTiger
$ sbt test
$ sbt run 
```

To deploy: 
  - I deployed with Heroku following this tutorial [Heroku for Play](https://devcenter.heroku.com/articles/getting-started-with-scala#introduction)
  - Currently running at this [address](https://boiling-fortress-23577.herokuapp.com/)
  
Supported commands:

  - /ttt and /ttt help will bring up an instruction set
  - /ttt @user will challenge a user to a game
  - /ttt game will display the current board
  - /ttt gameover will end the current game
  - /ttt move <int> will request a move at position int. To see the board
        configuration, you can run /ttt help
  
For more documentation on design decisions and models, see the project root directory TongueTiedTiger/

Thanks very much for checking it out! 
[my site](cs.dartmouth.edu/~pritikav)