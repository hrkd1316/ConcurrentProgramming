import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * 08章中級課題 ギブアップ
  */

case object Ping
case object Pong

class PingActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Ping => {
      log.info("ping")
      sender() ! Pong
    }
    case _ =>
  }
}

class PongActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Pong => {
      log.info("pong")
      sender() ! Ping
    }
    case _ =>
  }
}
object ReplyActorApp extends App {
  val system = ActorSystem("reply")

  val pingActor = system.actorOf(Props[PingActor], "pingActor")
  val pongActor = system.actorOf(Props[PongActor], "pongActor")

  pingActor.tell(Ping, pongActor)
}
