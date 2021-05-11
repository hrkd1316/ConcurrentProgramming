import java.security.MessageDigest

/**
  * 02章上級課題 ギブアップ
  */

object LockObjectMain2 extends App {

  for (i <- 1 to 100) {
    new Thread(() => println(HashDigestProvider1.digest("Hello!"))).start()
  }

}

object HashDigestProvider1 {

  def digest(str: String): List[Byte] =
    md.synchronized {
      val md = MessageDigest.getInstance("SHA-1")
      md.update(str.getBytes)
      md.digest().toList
    }

}
