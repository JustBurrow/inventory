package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import java.util.*

data class UpdateNounCmd(
        override val contextId: UUID,
        val manager: Int,
        val id: Int,
        val label: String,
        val labelCode: String,
        val limit: Int?,
        val description: String
) : FlowContext(contextId)
