import scala.collection.immutable.TreeMap

object CsvParse {

  def main(args: Array[String]) = {
    csvFileParser(args(0))
  }


  var configTree: TreeMap[Int, List[InputConfig]] = TreeMap()

  def csvFileParser(fileName: String): Unit = {

    for (row <- scala.io.Source.fromFile(fileName).getLines()) {

      val conf = row.toString.split(",").map(_.trim)
      val value = InputConfig(conf(0),
        conf(1),
        conf(2).toInt,
        conf(3),
        conf(4),
        Some(conf(4)))
      val key = conf(2).toInt
      configTree.get(key) match {
        case None => configTree += (key -> List(value))
        case Some(xs) => configTree += (key -> (value :: xs))
      }
    }

    println(configTree)
  }
}

case class InputConfig(A: String,
                       B: String,
                       ID: Int,
                       C: String,
                       D: String,
                       E: Option[String] = None)
