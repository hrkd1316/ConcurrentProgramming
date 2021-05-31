import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureOptionUsage extends App {

  val random = new Random()
  val waitMaxMilliSec = 3000

  val futureMilliSec: Future[Int] = Future {
    val waitMillSec = random.nextInt(waitMaxMilliSec);
    if (waitMillSec < 1000)
      throw new RuntimeException(s"waitMilliSec is ${waitMaxMilliSec}")
    Thread.sleep((waitMillSec))
    waitMillSec
  }

  val futureSec: Future[Double] = futureMilliSec.map(i => i.toDouble / 1000)

  futureSec onComplete {
    case Success(waitSec) => println(s"Success! ${waitSec} sec")
    case Failure(t)       => println(s"Failure: ${t.getMessage}")
  }

  Thread.sleep(3000)
}
