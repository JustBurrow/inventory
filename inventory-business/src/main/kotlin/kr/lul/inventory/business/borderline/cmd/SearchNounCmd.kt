package kr.lul.inventory.business.borderline.cmd

import kr.lul.inventory.design.util.FlowContext
import org.springframework.data.domain.Sort
import java.util.*

data class SearchNounCmd(
        override val contextId: UUID,
        val manager: Int,
        val page: Int,
        val size: Int,
        val sort: Sort
) : FlowContext(contextId)
