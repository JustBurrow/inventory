package kr.lul.inventory.test.business

import kr.lul.inventory.business.service.params.CreateItemParams
import kr.lul.inventory.test.data.ItemDataUtil

/**
 * @author justburrow
 * @since 2019-07-08
 */
class ItemBusinessUtil : ItemDataUtil() {
    fun createParams() = CreateItemParams(key(), label(), labelCode())
}
