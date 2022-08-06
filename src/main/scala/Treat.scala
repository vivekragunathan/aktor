sealed trait Treat

final case class Candy(name: String) extends Treat

case object NonCandy extends Treat
