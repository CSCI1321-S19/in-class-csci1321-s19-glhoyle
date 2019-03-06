package actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import Solution._

class Solution(img: WritableImage) extends Actor {
  def receive = {
    case CheckLocation(x, y) => 
      // he will finish code and push its
    case m => println("Unhandled message in Solution: " + m)
  }
}

object Solution {
  case class CheckLocation(x: Int, y: Int)
}