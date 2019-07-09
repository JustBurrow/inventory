package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateItemParams
import kr.lul.inventory.design.domain.Item

interface ItemService {
    fun create(params: CreateItemParams): Item
}
