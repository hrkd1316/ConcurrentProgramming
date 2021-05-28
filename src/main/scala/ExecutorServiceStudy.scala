import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.{Callable, Executors}

object ExecutorServiceStudy extends App {
  val es = Executors.newFixedThreadPool(10)
  val counter = new AtomicInteger(0)

  val futures = for (i <- 1 to 1000) yield {
    es.submit(new Callable[Int] {
      override def call(): Int = {
        val count = counter.incrementAndGet()
        println(count)
        Thread.sleep(100)
        count
      }
    })
  }

  println("sum: " + futures.foldLeft(0)((acc, f) => acc + f.get()))
  es.shutdownNow()
}

/**
  * 05章初級問題
  */
object TenThousandNamePrinter2 extends App {
  val es = Executors.newFixedThreadPool(10)

  for (i <- 1 to 10000) {
    es.submit(new Runnable {
      override def run(): Unit = {
        Thread.sleep(1000)
        println(Thread.currentThread().getName)
      }
    })
  }

  es.shutdownNow()
}
