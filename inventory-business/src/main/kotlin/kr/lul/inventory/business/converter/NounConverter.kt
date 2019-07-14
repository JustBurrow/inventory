package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Noun
import kotlin.reflect.KClass

/**
 * @author justburrow
 * @since 2019-07-09
 */
interface NounConverter {
    fun <T : Any> convert(noun: Noun, type: KClass<T>): T
}
