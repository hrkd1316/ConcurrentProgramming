import java.security.MessageDigest

/**
  * 02章中級課題
  */

object LockObjectMain1 extends App {

  for (i <- 1 to 100) {
    new Thread(() => println(HashDigestProvider1.digest("Hello!"))).start()
  }

}

object HashDigestProvider1 {
  private[this] val md = MessageDigest.getInstance("SHA-1")

  def digest(str: String): List[Byte] =
    md.synchronized {
      md.reset()
      md.update(str.getBytes)
      md.digest().toList
    }

}
