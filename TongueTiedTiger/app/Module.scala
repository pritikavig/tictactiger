import Engine.{TicTacToeEngineImpl, TicTacToeEngine}
import com.google.inject.AbstractModule
import datastore.{GameDAOMockImpl, GameDAO}

class Module extends AbstractModule {
  def configure() = {

    bind(classOf[TicTacToeEngine])
      .to(classOf[TicTacToeEngineImpl])

    bind(classOf[GameDAO])
      .to(classOf[GameDAOMockImpl])
  }
}