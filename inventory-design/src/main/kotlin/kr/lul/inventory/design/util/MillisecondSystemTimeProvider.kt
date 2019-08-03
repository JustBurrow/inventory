package kr.lul.inventory.design.util

import java.time.Instant
import java.time.ZoneId

class MillisecondSystemTimeProvider : TimeProvider {
    override val instant: Instant
        get() = Instant.ofEpochMilli(System.currentTimeMillis())

    override val zoneId: ZoneId = ZoneId.systemDefault()
}
