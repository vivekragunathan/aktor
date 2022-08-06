import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}

object CounterAdv {
  sealed trait Command
  final case object Increase extends Command

  def apply(init: Int, max: Int): Behavior[Command] =
    Behaviors.setup(new CounterAdv(init, max, _))

  class CounterAdv(init: Int, max: Int, context: ActorContext[Command]) extends AbstractBehavior[Command](context) {
    private var current = init

    override def onMessage(msg: Command): Behavior[Command] =
      msg match {
        case Increase =>
          current += 1

          if (current <= max) {
            context.log.info(s"increasing to $current")
            this
          } else {
            context.log.info(s"I'm overloaded. Counting '$current' while max is '$max")
            Behaviors.stopped
          }
      }
  }

  def main(args: Array[String]): Unit = {
    val guardian: ActorSystem[CounterAdv.Command] =
      ActorSystem(CounterAdv(0, 2), "counter-adv")

    guardian ! CounterAdv.Increase
    guardian ! CounterAdv.Increase
    guardian ! CounterAdv.Increase
    guardian ! CounterAdv.Increase
    guardian ! CounterAdv.Increase

  }
}
