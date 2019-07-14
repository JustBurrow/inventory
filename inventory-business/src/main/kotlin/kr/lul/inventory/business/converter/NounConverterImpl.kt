package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.dto.NounDetailDto
import kr.lul.inventory.dto.NounSimpleDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class NounConverterImpl : NounConverter {
    private val log = LoggerFactory.getLogger(NounConverterImpl::class.java)!!

    private fun detail(noun: Noun): NounDetailDto =
            NounDetailDto(noun.getId(), noun.getKey(), noun.getLabel(), noun.getLabelCode())

    private fun simple(noun: Noun): NounSimpleDto =
            NounSimpleDto(noun.getId(), noun.getKey(), noun.getLabel(), noun.getLabelCode())

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.converter.NounConverter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> convert(noun: Noun, type: KClass<T>): T {
        if (log.isTraceEnabled) log.trace("args : noun={}, type={}", noun, type)

        val dto = when (type) {
            NounDetailDto::class -> detail(noun) as T
            NounSimpleDto::class -> simple(noun) as T
            else -> throw IllegalArgumentException()
        }

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
