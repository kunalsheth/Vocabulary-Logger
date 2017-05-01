/**
  * Created by the-magical-llamicorn on 3/30/17.
  */
object Purifier {

  val pattern = "[\\P{L}\\p{N}]+".r

  def purify(text: String): Seq[String] = {
    pattern.split(text)
  }
}
