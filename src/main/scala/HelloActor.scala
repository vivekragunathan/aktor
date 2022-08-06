import akka.actor.typed.scaladsl.Behaviors

object HelloActor {
  def apply(): Behaviors.Receive[String] =
    Behaviors.receive {
      case (context, "Shutdown") =>
        context.log.info(s"Shutting down ...")
        Thread.sleep(2000)
        Behaviors.stopped

      case (context, message) =>
        context.log.info(s"received message '$message'")
        Behaviors.same
    }
}
