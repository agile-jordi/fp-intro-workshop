package com.agilogy.fpintro.messages.infrastructure

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import scala.collection.JavaConverters._
import scala.util.matching.Regex

import com.agilogy.fpintro.effects.id.Id.Id
import com.agilogy.fpintro.messages.app.MessagesRepository
import com.agilogy.fpintro.messages.domain.{Emoji, Message, MessageContent}

// Id allows us to see ImperativeMessagesRepository as an implementation of MessagesRepository where our F[_] is Id
// That is, methods returning F[Unit] now return Id[Unit], which is Unit and same for F[Message] to Message
object ImperativeMessagesRepository extends MessagesRepository[Id] {

  private val path                 = Paths.get("./messages.txt").toAbsolutePath
  private val StoredMessage: Regex = s"(.*) -- ?(.*)".r

  override def selectByContent(content: MessageContent): Message =
    selectAll().find(_.content == content).getOrElse(throw new MessageNotFoundException)

  override def update(message: Message): Unit = {
    val currentMessages = selectAll()
    if (!currentMessages.exists(_.content == message.content)) throw new MessageNotFoundException()
    val newMessages = selectAll().map {
      case Message(content, _) if content == message.content => message
      case m                                                 => m
    }
    writeMessages(newMessages)
  }

  private def selectAll(): List[Message] = {
    val lines = Files.readAllLines(path).asScala
    lines.map {
      case StoredMessage(message, reactions) =>
        Message(MessageContent(message), reactions.split(",").filter(_.nonEmpty).map(Emoji(_)).toList)
    }.toList
  }

  private def writeMessages(messages: List[Message]): Unit = {
    val fileContent = messages
      .map { message =>
        val content   = message.content.value
        val reactions = message.reactions.map(_.value).mkString(",")
        s"$content -- $reactions"
      }
      .mkString("\n")
    println(s"Saving...\n$fileContent\n")
    Files.write(path, fileContent.getBytes(StandardCharsets.UTF_8))
  }

}
