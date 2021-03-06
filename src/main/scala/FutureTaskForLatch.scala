import java.util.concurrent.FutureTask

/**
  * 04章中級課題
  */
object FutureTaskForLatch extends App {

  val futureTasks = for (i <- 1 to 3) yield new FutureTask[Int](() => {
    Thread.sleep(1000)
    println(s"FutureTask ${i} finished")
    i
  })
  futureTasks.foreach((f) => new Thread(f).start())

  new Thread(() => {
    futureTasks.foreach(_.get())
    println("All finished")
  }).start()
}
