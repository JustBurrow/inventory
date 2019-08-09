package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import java.util.*

/**
 * @author justburrow
 * @since 2019-08-09
 */
data class CreateLimitedIdentifiableNounParams(
        override val contextId: UUID,
        override val manager: Manager,
        override val key: String,
        override val label: String,
        override val labelCode: String,
        val limit: Int,
        override val description: String,
        override val createAt: Instant
) : AbstractCreateNounParams(contextId, manager, key, label, labelCode, description, createAt) {
    override fun toString(): String = "(contextId=$contextId, manager=${manager.simpleString}, key='$key', " +
            "label='$label', labelCode='$labelCode', limit=$limit, description='$description', createAt=$createAt)"
}