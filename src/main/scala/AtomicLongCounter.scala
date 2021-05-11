/**
  * 02章初級課題
  */

import java.util.concurrent.atomic.AtomicLong

object AtomicLongCounterMain extends App {

  for (i <- 1 to 100) {
    new Thread(() => println(AtomicLongCounter.next)).start()
  }
}

object AtomicLongCounter {
  val num = new AtomicLong(0)
  def next: Long = num.incrementAndGet()
}
