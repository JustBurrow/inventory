package kr.lul.inventory.business.converter

import kr.lul.inventory.design.domain.Manager
import kotlin.reflect.KClass

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerConverter {
    fun <T : Any> convert(manager: Manager, type: KClass<T>): T
}
