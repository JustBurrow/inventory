package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import java.util.*

data class ReadNounCmd(
        override val contextId: UUID,
        val manager: Int,
        val id: Int
) : FlowContext(contextId)
