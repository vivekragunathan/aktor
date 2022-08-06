import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object Counter {
  sealed trait Command
  final case object Increase extends Command

  def apply(init: Int, max: Int): Behavior[Command] =
    Behaviors.receive { case (context, Increase) =>
      val current = init + 1

      if (current > max) {
        context.log.info(s"I'm overloaded. Counting '$current' while max is '$max")
        Behaviors.stopped
      } else {
        context.log.info(s"increasing to $current")
        apply(current, max)
      }
    }

  def main(args: Array[String]): Unit = {
    val guardian: ActorSystem[Counter.Command] = ActorSystem(Counter(0, 2), "counter")

    guardian ! Counter.Increase
    guardian ! Counter.Increase
    guardian ! Counter.Increase
  }
}
