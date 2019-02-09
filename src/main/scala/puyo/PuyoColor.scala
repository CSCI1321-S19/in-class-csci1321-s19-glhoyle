package puyo

object PuyoColor extends Enumeration {
  val Red, Yellow, Blue, Green, Gray, Magenta = Value
  
  val puyoColors = (values - Gray).toSeq
  
  def random(): Value = {
    puyoColors(util.Random.nextInt(5))
  }
}