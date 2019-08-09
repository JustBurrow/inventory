package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import java.util.*

open class AbstractCreateNounParams(
        open val contextId: UUID,
        open val manager: Manager,
        open val key: String,
        open val label: String,
        open val labelCode: String,
        open val description: String,
        open val createAt: Instant)
