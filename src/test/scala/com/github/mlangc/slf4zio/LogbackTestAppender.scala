package com.github.mlangc.slf4zio

import java.util.concurrent.atomic.AtomicReference

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import zio.UIO

class LogbackTestAppender extends AppenderBase[ILoggingEvent] {
  def append(eventObject: ILoggingEvent): Unit = {
    LogbackTestAppender.eventsRef.updateAndGet(eventObject :: _)
    ()
  }
}

object LogbackTestAppender {
  private val eventsRef = new AtomicReference(List.empty[ILoggingEvent])

  def events: UIO[List[ILoggingEvent]] =
    UIO(eventsRef.get())
}
