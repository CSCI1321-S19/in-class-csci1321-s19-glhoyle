package basics

import basics.adt.ArrayQueue
import collection.mutable

object Utility extends App {
  def findAndRemove[A](lst: List[A])(pred: A => Boolean): (Option[A], List[A]) = {
    lst match {
      case Nil => (None, Nil)
      case h :: t =>
        if(pred(h)) (Some(h), t) else {
          val (found, fixedList) = findAndRemove(t)(pred)
          (found, h :: fixedList)
        }
    }
  }
  
  val nums = List(1,2,3,4,5,6,7,8,9)
  val (five, notFive) = findAndRemove(nums)(_ == 5)
  println(five, notFive)
  
  val maze = Array(Array( 0,-1, 0,-1, 0, 0, 0, 0, 0, 0),
                   Array( 0,-1, 0, 0, 0,-1,-1, 0,-1, 0),
                   Array( 0,-1, 0,-1, 0,-1, 0, 0,-1, 0),
                   Array( 0,-1, 0,-1, 0,-1, 0,-1, 0, 0),
                   Array( 0, 0, 0,-1,-1,-1, 0, 0, 0, 0),
                   Array(-1,-1,-1, 0, 0, 0, 0, 0, 0, 0),
                   Array( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                   Array( 0,-1, 0, 0, 0, 0, 0, 0, 0, 0),
                   Array( 0,-1, 0, 0, 0, 0, 0, 0, 0, 0),
                   Array( 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
                   
  val offsets = List((0, -1), (0, 1), (-1, 0), (1, 0))
  // TODO - add tuples to offsets to add diagonal. also get diagonal working
  // TODO - to use shortestPath, run it four times and take the shortest number
  // TODO - do not assume the starting location is safe
  // TODO - if return steps will never run
  // TODO - only use shortestPath when enemy is close to player

  def shortestPath(sx: Int, sy: Int, ex: Int, ey: Int, maze: Array[Array[Int]]): Int = {
    val queue = new ArrayQueue[(Int, Int, Int)]()
    queue.enqueue((sx, sy, 0))
    val visited = mutable.Set[(Int, Int)](sx -> sy)
    
    while (!queue.isEmpty) {
      val (x, y, steps) = queue.dequeue()
      for ((dx, dy) <- offsets) {
        val nx = x + dx
        val ny = y + dy
        if (nx == ex && ny == ey) return steps
        // Dr. Lewis HATES it - return's One Weird Trick
        if ((nx >= 0 && ny >= 0 && nx < maze.length && ny < maze(nx).length) && maze(nx)(ny) == 0 && !visited(nx -> ny)) {
          queue.enqueue((nx, ny, steps + 1))
          visited += nx -> ny
        }
      }
    }
    -100000000
  }
  
  println(shortestPath(0, 0, 9, 9, maze))
  
}