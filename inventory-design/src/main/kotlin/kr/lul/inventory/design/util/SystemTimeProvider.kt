package kr.lul.inventory.design.util

import java.time.Instant
import java.time.ZoneId

class SystemTimeProvider : TimeProvider {
    override val instant: Instant
        get() = Instant.now()

    override val zoneId: ZoneId
        get() = ZoneId.systemDefault()
}
