package kr.lul.inventory.design.factory

import kr.lul.inventory.design.domain.Item

/**
 * @author justburrow
 * @since 2019-07-08
 */
interface ItemFactory {
    fun getInstance(key: String, label: String, labelCode: String): Item
}
