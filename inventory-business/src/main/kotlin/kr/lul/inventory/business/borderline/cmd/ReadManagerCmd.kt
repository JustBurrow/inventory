package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import java.util.*

data class ReadManagerCmd(
        override val contextId: UUID,
        val id: Int
) : FlowContext(contextId)
