import java.text.SimpleDateFormat
import java.util.Date

/**
  * 03章上級課題
  */
object ThreadSafeFormatter extends App {

  def format(date: Date) = {
    val formatter = new SimpleDateFormat("yyyy'年'MM'月'dd'日'E'曜日'H'時'mm'分'")
    formatter.format(date)
  }

}
