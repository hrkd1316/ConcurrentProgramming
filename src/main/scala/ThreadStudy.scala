object ThreadStudy extends App {
  println(Thread.currentThread().getName)

  val thread = new Thread(() => {
    Thread.sleep(1000)
    println(Thread.currentThread().getName)
  })
  thread.start()

  println("main thread finished.")
}

/**
 * 01章初級課題
 */
object QuadNumberPrinter extends App {

  for (i <- 1 to 4) {
    new Thread(() => for(j <- 1 to 100000)  println(s"thread ${i}: ${j}")).start()
  }

}

/**
 * 01章中級課題
 */
object TenThousandNamePrinter extends App {

  for (i <- 1 to 10000) {
    new Thread(() => {
      Thread.sleep(1000)
      println(Thread.currentThread().getName)
    }).start()
  }

}

/**
 * 01章上級課題
 */
object TwoThreads extends App {
  var now: Long = 0L

  val threadA = new Thread(() => synchronized {
    Thread.sleep(1000)
    now = System.currentTimeMillis()
  })
  val threadB = new Thread(() => synchronized {
    while (now == 0L) {
      Thread.sleep(1000)
    }
    println(now)
  })

  threadA.start()
  threadB.start()
}