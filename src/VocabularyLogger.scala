import scala.reflect.io.File

/**
  * Created by the-magical-llamicorn on 3/30/17.
  */
object VocabularyLogger extends App {

  val sourceFile = File(readLine("File to generate vocabulary from (File Path): "))

  val allWords = Dictionary.getWords(sourceFile.lines)

  val chronologicalWords = sourceFile.lines()
    .flatMap(Purifier.purify)
    .toStream
    .distinct
    .zipWithIndex
    .toMap

  val usedWords = File(readLine("File with list of old words (File Path): ")).lines
    .flatMap(Purifier.purify)
    .toSet

  println("Number of words to log (Integer): ")
  allWords
    .filter(w => !usedWords.contains(w))
    .take(readInt)
    .sortBy(chronologicalWords)
    .foreach(println)
}
