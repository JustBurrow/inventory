package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Item
import kotlin.reflect.KClass

/**
 * @author justburrow
 * @since 2019-07-09
 */
interface ItemConverter {
    fun <T : Any> convert(item: Item, type: KClass<T>): T
}
