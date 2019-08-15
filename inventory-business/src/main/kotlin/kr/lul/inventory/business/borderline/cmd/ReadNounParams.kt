package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.util.FlowContext
import java.util.*

/**
 * @author justburrow
 * @since 2019-08-15
 */
data class ReadNounParams(
        override val contextId: UUID,
        val manager: Manager,
        val id: Int
) : FlowContext(contextId)
