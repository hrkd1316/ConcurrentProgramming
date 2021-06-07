import akka.actor.{Actor, ActorRef, ActorSystem, Inbox, Props}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}

/**
  * 09章 初級課題
  */
class ParentActor extends Actor {

  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._
  import scala.concurrent.duration._

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Exception => Resume
    }

  def receive = {
    case p: Props => sender() ! context.actorOf(p)
  }
}

class ChildActor extends Actor {
  var state = 0
  def receive = {
    case ex: Exception => throw ex
    case s: String     => println(s)
  }
}

object MustResume extends App {
  val system = ActorSystem("MustResume")
  val inbox = Inbox.create(system)
  implicit val sender = inbox.getRef()

  val parent = system.actorOf(Props[ParentActor], "parent")

  parent ! Props[ChildActor]
  val child = inbox.receive(5.seconds).asInstanceOf[ActorRef]

  child ! new RuntimeException

  Thread.currentThread().join()
}
