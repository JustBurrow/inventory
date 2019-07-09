package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.dto.ItemDetailDto
import kr.lul.inventory.dto.ItemSimpleDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
internal class ItemConverterImpl : ItemConverter {
    private val log = LoggerFactory.getLogger(ItemConverterImpl::class.java)!!

    private fun detail(item: Item): ItemDetailDto =
            ItemDetailDto(item.getId(), item.getKey(), item.getLabel(), item.getLabelCode())

    private fun simple(item: Item): ItemSimpleDto =
            ItemSimpleDto(item.getId(), item.getKey(), item.getLabel(), item.getLabelCode())

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.converter.ItemConverter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> convert(item: Item, type: KClass<T>): T {
        if (log.isTraceEnabled) log.trace("args : item={}, type={}", item, type)

        val dto: T = when (type) {
            ItemDetailDto::class -> detail(item) as T
            ItemSimpleDto::class -> simple(item) as T
            else -> throw IllegalArgumentException()
        }

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
