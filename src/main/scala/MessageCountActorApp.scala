import akka.actor.{ActorSystem, Props, Actor}

/**
  * 08章初級課題
  */
class countActor extends Actor {
  var count = 0
  def receive = {
    case _ => {
      count = count + 1
      println(s"Message count: $count")
    }
  }
}
object MessageCountActorApp extends App {
  val system = ActorSystem("messageCount")

  val counter = system.actorOf(Props[countActor], "counter")

  for (i <- 1 to 10000) counter ! "hello"

  Thread.currentThread().join()
}
