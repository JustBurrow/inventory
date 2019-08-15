package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateCountableNounParams
import kr.lul.inventory.business.service.params.CreateIdentifiableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedCountableNounParams
import kr.lul.inventory.business.service.params.CreateLimitedIdentifiableNounParams
import kr.lul.inventory.business.service.params.SearchNounParams
import kr.lul.inventory.design.domain.CountableNoun
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.InvalidAttributeException
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.LimitedIdentifiableNoun
import kr.lul.inventory.design.domain.Noun
import org.springframework.data.domain.Page

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

    fun search(params: SearchNounParams): Page<Noun>
}
