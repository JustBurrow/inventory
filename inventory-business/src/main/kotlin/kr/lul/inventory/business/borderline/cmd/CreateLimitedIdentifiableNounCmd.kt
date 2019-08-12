package kr.lul.inventory.business.borderline.cmd

import java.util.*

data class CreateLimitedIdentifiableNounCmd(
        override val contextId: UUID,
        override val manager: Int,
        override val key: String,
        override val label: String,
        override val labelCode: String,
        val limit: Int,
        override val description: String
) : AbstractCreateNounCmd(contextId, manager, key, label, labelCode, description)
