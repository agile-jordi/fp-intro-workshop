package com.agilogy.fpintro.users.infrastructure

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import scala.util.matching.Regex

import com.agilogy.fpintro.users.domain.{Emoji, Message, MessageContent}
import scala.collection.JavaConverters._

final class DuplicateMessageException() extends Exception
final class MessageNotFoundException()  extends Exception

object MessagesRepository {

  private val path                 = Paths.get("./messages.txt").toAbsolutePath
  private val StoredMessage: Regex = s"(.*) -- (.*)".r

  def deleteAll(): Unit = writeMessages(List.empty)

  def selectAll(): List[Message] = {
    val lines = Files.readAllLines(path).asScala
    lines.map {
      case StoredMessage(message, reactions) =>
        Message(MessageContent(message), reactions.split(",").filter(_.nonEmpty).map(Emoji(_)).toList)
    }.toList
  }

  def selectByContent(content: MessageContent): Option[Message] = selectAll().find(_.content == content)

  def insert(messageContent: MessageContent): Unit = {
    val currentMessages = selectAll()
    if (currentMessages.exists(_.content == messageContent)) throw new DuplicateMessageException()
    val newMessages = Message(messageContent) :: currentMessages
    writeMessages(newMessages)
  }

  def update(message: Message): Unit = {
    val currentMessages = selectAll()
    if (!currentMessages.exists(_.content == message.content)) throw new MessageNotFoundException()
    val newMessages = selectAll().map {
      case Message(content, _) if content == message.content => message
      case m                                                 => m
    }
    writeMessages(newMessages)
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
