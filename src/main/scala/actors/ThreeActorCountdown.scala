package actors

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

object ThreeActorCountdown extends App {
  class CountingActor extends Actor {
    def receive = {
      case StartCounting(i, next, nextNext) =>
        println(i)
        next ! Countdown(i-1, nextNext)
      case Countdown(i, next) =>
        if (i > 0) {
          println(i)
          next ! Countdown(i-1, sender)
        } else {
          system.terminate()
          // ALWAYS ADD THIS
        }
      case m => println("Unhandled message in CountingActor: " + m)
      // ^ add this to EVERY ACTOR
    }
  }
  
  val system = ActorSystem("ThreeActorCountdown")
  // you need this to make it work
  
  val actor1 = system.actorOf(Props[CountingActor], "Mark")
  val actor2 = system.actorOf(Props[CountingActor], "David")
  val actor3 = system.actorOf(Props[CountingActor], "Sara")
  
  actor1 ! StartCounting(10, actor2, actor3)
  
  case class StartCounting(i: Int, next: ActorRef, nextNext: ActorRef)
  case class Countdown(i: Int, next: ActorRef)
  // use next to pass to next actor
  // declare messages as case classes to keep them immutable
}