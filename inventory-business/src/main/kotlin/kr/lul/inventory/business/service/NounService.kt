package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateNounParams
import kr.lul.inventory.design.domain.Noun

interface NounService {
    fun create(params: CreateNounParams): Noun
}
