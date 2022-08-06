import akka.actor.typed.ActorSystem

object HelloApp extends App {
  val guardian: ActorSystem[String] =
    ActorSystem(
      guardianBehavior = HelloActor(),
      name = "HelloActor"
    )

  guardian ! "Hello"
  guardian ! "Hello Again"
  guardian ! "Shutdown"
}
