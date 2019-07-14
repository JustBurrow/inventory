package kr.lul.inventory.data.dao

import kr.lul.inventory.design.domain.Noun

interface NounDao {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.data.dao.NounDao
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun create(noun: Noun): Noun
}
