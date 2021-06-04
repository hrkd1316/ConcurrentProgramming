import akka.actor.{ActorSystem, Props, Actor}
import akka.event.Logging

/**
  * 08章上級課題
  */

case class TimeMessage(time: String)

class TooMuchMessageActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case TimeMessage(time) => {
      log.info(time)
      Thread.sleep(1000)
    }
  }
}
object TooMuchMessageApp extends App {
  val system = ActorSystem("helloAkka")

  val tooMuchMessageActor =
    system.actorOf(Props[TooMuchMessageActor], "tooMuchMessageActor")

  while (true) {
    tooMuchMessageActor ! TimeMessage(java.time.LocalDateTime.now().toString)
  }
}
