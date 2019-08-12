package kr.lul.inventory.dto

data class ManagerSimpleDto(
        override val id: Int,
        override val name: String
) : ManagerDto
