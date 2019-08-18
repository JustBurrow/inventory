package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.NounType
import kr.lul.inventory.design.util.FlowContext
import java.time.Instant
import java.util.*

open class AbstractUpdateNounParams(
        contextId: UUID,
        open val manager: Manager,
        open val id: Int,
        open val type: NounType,
        open val timestamp: Instant
) : FlowContext(contextId)
