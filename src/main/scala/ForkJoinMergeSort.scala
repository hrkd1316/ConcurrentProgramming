import scala.util.Random
import java.util.concurrent.{ForkJoinPool, RecursiveTask}

/**
  * 06章初級課題
  * Q. 処理の 20% が直列でしか行えない処理を並行処理を利用して並列化した場合、 最大で何倍の効率を得ることができるでしょうか。
  * A. 5倍
  */

/**
  * 06章中級課題
  * Q. 処理 36% が直列でしか行えない処理を 4 コアを持つ CPU で並行処理を利用して並列化した場合、 最大で何倍の効率を得ることができるでしょうか。
  * A. 1.92倍
  */

/**
  * 06章上級課題
  */

object ForkJoinMergeSort extends App {
  val length = 100
  val randomList: List[Int] =
    (for (i <- 1 to length) yield Random.nextInt(100)).toList
  println("Before mergesort: ", randomList)

  val pool = new ForkJoinPool()

  class AggregateTask(list: List[Int]) extends RecursiveTask[List[Int]] {

    override def compute(): List[Int] = {
      val n = list.length / 2
      if (n == 0) {
        list match {
          case List()  => Nil
          case List(n) => List(n)
        }
      } else {
        val (left, right) = list.splitAt(n)
        val leftTask = new AggregateTask((left))
        val rightTask = new AggregateTask((right))
        leftTask.fork()
        rightTask.fork()
        merge(leftTask.join(), rightTask.join())
      }
    }

    private[this] def merge(
        leftList: List[Int],
        rightList: List[Int]
    ): List[Int] = {
      (leftList, rightList) match {
        case (left, Nil)  => left
        case (Nil, right) => right
        case (leftHead :: leftTail, rightHead :: rightTail) =>
          if (leftHead < rightHead) leftHead :: merge(leftTail, rightList)
          else rightHead :: merge(leftList, rightTail)
      }
    }
  }
  val sortedList: List[Int] = pool.invoke(new AggregateTask(randomList))
  println("After mergesort: ", sortedList)
}
