package kr.lul.inventory.dto

import java.time.ZonedDateTime

/**
 * @author justburrow
 * @since 2019-07-14
 */
data class ManagerDetailDto(
        val id: Int,
        val email: String,
        val name: String,
        val createdAt: ZonedDateTime
)
