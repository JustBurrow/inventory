package kr.lul.inventory.business.service.params

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.util.FlowContext
import org.springframework.data.domain.Sort
import java.util.*

data class SearchNounParams(
        override val contextId: UUID,
        val manager: Manager,
        val page: Int,
        val size: Int,
        val sort: Sort
) : FlowContext(contextId)
