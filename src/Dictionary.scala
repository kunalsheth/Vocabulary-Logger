import scala.collection.mutable

/**
  * Created by the-magical-llamicorn on 3/30/17.
  */
object Dictionary {

  def getWords(corpus: TraversableOnce[String]): Seq[String] = {
    val bag = new mutable.HashMap[String, Int]()

    corpus
      .flatMap(Purifier.purify)
      .foreach(w => bag.put(w, bag.getOrElse(w, 0) + 1))

    bag.toSeq
      .filter(t => t._2 > 1)
      .sortBy(t => t._2)
      .map(_._1)
  }
}
