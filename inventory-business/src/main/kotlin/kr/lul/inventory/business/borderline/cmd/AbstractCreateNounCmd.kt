package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import java.util.*

abstract class AbstractCreateNounCmd(
        contextId: UUID,
        open val manager: Int,
        open val key: String,
        open val label: String,
        open val labelCode: String,
        open val description: String
) : FlowContext(contextId)
