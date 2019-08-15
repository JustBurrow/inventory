package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.CountableNoun
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.LimitedIdentifiableNoun
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.dto.CountableNounDetailDto
import kr.lul.inventory.dto.IdentifiableNounDetailDto
import kr.lul.inventory.dto.LimitedCountableNounDetailDto
import kr.lul.inventory.dto.LimitedIdentifiableNounDetailDto
import kr.lul.inventory.dto.ManagerSimpleDto
import kr.lul.inventory.dto.NounSimpleDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class NounConverterImpl : NounConverter {
    private val log = LoggerFactory.getLogger(NounConverterImpl::class.java)!!

    @Autowired
    private lateinit var managerConverter: ManagerConverter
    @Autowired
    private lateinit var timeProvider: TimeProvider

    private fun nounSimple(noun: Noun) = NounSimpleDto(
            noun.id,
            noun.type,
            noun.key,
            noun.label,
            noun.labelCode)

    private fun countableDetail(noun: CountableNoun): CountableNounDetailDto = CountableNounDetailDto(
            noun.id,
            managerConverter.convert(noun.manager, ManagerSimpleDto::class),
            noun.key,
            noun.label,
            noun.labelCode,
            noun.description,
            timeProvider.toZoneDateTime(noun.createdAt),
            timeProvider.toZoneDateTime(noun.updatedAt))

    private fun identifiableDetail(noun: IdentifiableNoun) = IdentifiableNounDetailDto(
            noun.id,
            managerConverter.convert(noun.manager, ManagerSimpleDto::class),
            noun.key,
            noun.label,
            noun.labelCode,
            noun.description,
            timeProvider.toZoneDateTime(noun.createdAt),
            timeProvider.toZoneDateTime(noun.updatedAt))

    private fun limitedCountableDetail(noun: LimitedCountableNoun) = LimitedCountableNounDetailDto(
            noun.id,
            managerConverter.convert(noun.manager, ManagerSimpleDto::class),
            noun.key,
            noun.label,
            noun.labelCode,
            noun.limit,
            noun.description,
            timeProvider.toZoneDateTime(noun.createdAt),
            timeProvider.toZoneDateTime(noun.updatedAt))

    private fun limitedIdentifiable(noun: LimitedIdentifiableNoun) = LimitedIdentifiableNounDetailDto(
            noun.id,
            managerConverter.convert(noun.manager, ManagerSimpleDto::class),
            noun.key,
            noun.label,
            noun.labelCode,
            noun.limit,
            noun.description,
            timeProvider.toZoneDateTime(noun.createdAt),
            timeProvider.toZoneDateTime(noun.updatedAt))

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.converter.NounConverter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> convert(noun: Noun, type: KClass<T>): T {
        if (log.isTraceEnabled) log.trace("args : noun={}, type={}", noun, type)

        val dto = when (type) {
            NounSimpleDto::class -> nounSimple(noun) as T
            CountableNounDetailDto::class -> countableDetail(noun as CountableNoun) as T
            IdentifiableNounDetailDto::class -> identifiableDetail(noun as IdentifiableNoun) as T
            LimitedCountableNounDetailDto::class -> limitedCountableDetail(noun as LimitedCountableNoun) as T
            LimitedIdentifiableNounDetailDto::class -> limitedIdentifiable(noun as LimitedIdentifiableNoun) as T
            else -> throw IllegalArgumentException("unsupported type : $type")
        }

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
