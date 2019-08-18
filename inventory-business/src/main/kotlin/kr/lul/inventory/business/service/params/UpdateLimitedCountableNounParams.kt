package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.NounType.LIMITED_COUNTABLE
import java.time.Instant
import java.util.*

data class UpdateLimitedCountableNounParams(
        override val contextId: UUID,
        override val manager: Manager,
        override val id: Int,
        val label: String,
        val labelCode: String,
        val limit: Int,
        val description: String,
        override val timestamp: Instant
) : AbstractUpdateNounParams(contextId, manager, id, LIMITED_COUNTABLE, timestamp)
