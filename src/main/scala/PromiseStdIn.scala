import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Promise, Future}
import scala.util.{Failure, Success}

/**
  * 07章中級課題
  */
object PromiseStdIn extends App {

  def applyFromStdIn(lineInputProcessor: Int => Unit): Unit = {
    lineInputProcessor(io.StdIn.readLine().toInt)
  }

  val future: Future[Int] = {
    val promise: Promise[Int] = Promise[Int]
    applyFromStdIn(i => promise.success(i * 7))
    promise.future
  }

  future onComplete {
    case Success(value)     => println(value)
    case Failure(throwable) => throwable.printStackTrace()
  }
  Await.result(future, Duration.Inf)
}
