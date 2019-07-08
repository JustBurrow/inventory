package kr.lul.inventory.data.jpa.factory

import kr.lul.inventory.data.jpa.entity.ItemEntity
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.design.factory.ItemFactory
import org.springframework.stereotype.Component

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Component
class ItemFactoryImpl : ItemFactory {
    override fun getInstance(key: String, label: String, labelCode: String): Item = ItemEntity(key, label, labelCode)
}
