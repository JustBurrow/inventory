package kr.lul.inventory.dto

import kr.lul.inventory.design.domain.NounType
import java.time.ZonedDateTime

data class LimitedCountableNounDetailDto(
        override val id: Int,
        override val manager: ManagerSimpleDto,
        override val key: String,
        override val label: String,
        override val labelCode: String,
        val limit: Int,
        override val description: String,
        override val createdAt: ZonedDateTime,
        override val updatedAt: ZonedDateTime
) : NounDetailDto {
    override val type = NounType.LIMITED_COUNTABLE
}
