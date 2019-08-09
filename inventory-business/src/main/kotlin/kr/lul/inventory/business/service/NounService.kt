package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateCountableNounParams
import kr.lul.inventory.business.service.params.CreateIdentifiableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedIdentifiableNounParams
import kr.lul.inventory.design.domain.*

/**
 * @author justburrow
 * @since 2019-08-04
 */
interface NounService {
    @Throws(InvalidAttributeException::class)
    fun create(params: CreateIdentifiableNounParams): IdentifiableNoun

    @Throws(InvalidAttributeException::class)
    fun create(params: CreateCountableNounParams): CountableNoun

    @Throws(InvalidAttributeException::class)
    fun create(params: CreateLimitedIdentifiableNounParams): LimitedIdentifiableNoun

    @Throws(InvalidAttributeException::class)
    fun create(params: CreateLimitedCountableNounParams): LimitedCountableNoun

    /**
     * @return [Noun] 인스턴스. 없으면 `null`.
     */
    fun <N : Noun> read(id: Int): N?
}
