package kr.lul.inventory.dto

import java.time.ZonedDateTime

interface NounDetailDto : NounDto {
    val manager: ManagerSimpleDto

    val description: String

    val createdAt: ZonedDateTime

    val updatedAt: ZonedDateTime
}
