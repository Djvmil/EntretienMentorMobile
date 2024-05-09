package com.djvmil.entretienmentor.core.data.source.db

import app.cash.sqldelight.ColumnAdapter
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder

object TimestampInstantAdapter: ColumnAdapter<Instant, String> {
    private val dateTimeFormat = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd HH:mm:ss")
        .toFormatter()
        .withZone(ZoneId.of("UTC"))

    override fun decode(databaseValue: String) = dateTimeFormat.parse(databaseValue, Instant::from)
    override fun encode(value: Instant) = dateTimeFormat.format(value)
}