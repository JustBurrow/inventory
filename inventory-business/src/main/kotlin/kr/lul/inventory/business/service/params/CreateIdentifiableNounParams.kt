package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import java.util.*

data class CreateIdentifiableNounParams(
        override val contextId: UUID,
        override val manager: Manager,
        override val key: String,
        override val label: String,
        override val labelCode: String,
        override val description: String,
        override val createAt: Instant
) : AbstractCreateNounParams(contextId, manager, key, label, labelCode, description, createAt) {
    override fun toString(): String = "(contextId=$contextId, manager=${manager.simpleString}, key='$key', " +
            "label='$label', labelCode='$labelCode', description='$description', createAt=$createAt)"
}
