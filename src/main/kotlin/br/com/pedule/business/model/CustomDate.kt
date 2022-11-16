package br.com.pedule.business.model

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun getDateof(date: LocalDateTime): ZonedDateTime {
    return ZonedDateTime.of(date, ZoneId.systemDefault())
}