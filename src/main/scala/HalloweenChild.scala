import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

object HalloweenChild {
  def apply(): Behavior[Treat] =
    Behaviors.receive {
      case (context, Candy(name)) =>
        context.log.info(s"this is so much fun. I love $name")
        Behaviors.same

      case (context, NonCandy) =>
        context.log.info("this is not fun. I'm going home")
        Behaviors.stopped
    }

  def main(args: Array[String]): Unit = {
    val guardian: ActorSystem[Treat] = ActorSystem(HalloweenChild(), "halloween")

    guardian ! Candy("chocolate bar")
    guardian ! NonCandy
    guardian ! Candy("chocolate bar")
  }
}
