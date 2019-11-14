package com.agilogy.fpintro.messages.domain

final case class Emoji(value: String)

object Emoji {
  val PleadingFace: Emoji = Emoji("🥺")
  val Laughing: Emoji     = Emoji("😂")
  val HeartEyes: Emoji    = Emoji("😍")
  val Like: Emoji         = Emoji("👍")
  val Dislike: Emoji      = Emoji("👎")
  val ThinkingFace: Emoji = Emoji("🤔")
  val Sun: Emoji          = Emoji("☀️")
  val Tada: Emoji         = Emoji("🎉")
}

final case class MessageContent(value: String)

final case class Message(content: MessageContent, reactions: List[Emoji] = List.empty) {
  def addReaction(reaction: Emoji): Message = this.copy(reactions = reaction :: reactions)
}
