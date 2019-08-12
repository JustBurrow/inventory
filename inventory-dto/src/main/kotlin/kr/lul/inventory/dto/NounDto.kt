package kr.lul.inventory.dto

import kr.lul.inventory.design.domain.NounType

interface NounDto {
    val id: Int
    val type: NounType
    val key: String
    val label: String
    val labelCode: String
}
