package kr.lul.inventory.design.util

import java.time.*

/**
 * @author justburrow
 * @since 2019-07-23
 */
interface TimeProvider {
    val instant: Instant

    val zoneId: ZoneId

    val zoneOffset: ZoneOffset
        get() = zoneId.rules.getOffset(instant)

    val zonedDateTime: ZonedDateTime
        get() = ZonedDateTime.ofInstant(instant, zoneId)

    val offsetDateTime: OffsetDateTime
        get() = OffsetDateTime.ofInstant(instant, zoneId)

    val offsetTime: OffsetTime
        get() = OffsetTime.ofInstant(instant, zoneId)

    val localDateTime: LocalDateTime
        get() = LocalDateTime.ofInstant(instant, zoneId)

    val localDate: LocalDate
        get() = LocalDate.ofInstant(instant, zoneId)

    val localTime: LocalTime
        get() = LocalTime.ofInstant(instant, zoneId)

    fun toZoneOffset(zoneId: ZoneId) = zoneId.rules.getOffset(instant)

    fun toZoneDateTime(instant: Instant) = ZonedDateTime.ofInstant(instant, zoneId)!!

    fun toOffsetDateTime(instant: Instant) = OffsetDateTime.ofInstant(instant, zoneId)!!

    fun toOffsetTime(instant: Instant) = OffsetTime.ofInstant(instant, zoneId)!!

    fun toLocalDateTime(instant: Instant) = LocalDateTime.ofInstant(instant, zoneId)!!

    fun toLocalDateTime(zonedDateTime: ZonedDateTime) = zonedDateTime.toLocalDateTime()!!

    fun toLocalDate(instant: Instant) = LocalDate.ofInstant(instant, zoneId)!!

    fun toLocalDate(zonedDateTime: ZonedDateTime) = zonedDateTime.toLocalDate()!!

    fun toLocalTime(instant: Instant) = LocalTime.ofInstant(instant, zoneId)!!

    fun toLocalTime(zonedDateTime: ZonedDateTime) = zonedDateTime.toLocalTime()!!
}
