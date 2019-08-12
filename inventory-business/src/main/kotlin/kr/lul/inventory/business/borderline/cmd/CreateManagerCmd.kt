package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import java.util.*

data class CreateManagerCmd(
        override val contextId: UUID,
        val email: String,
        val name: String,
        val password: String
) : FlowContext(contextId)
