package kr.lul.inventory.test.data

import kr.lul.inventory.data.jpa.entity.ItemEntity
import kr.lul.inventory.design.domain.Item
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

/**
 * @author justburrow
 * @since 2019-07-06
 */
open class ItemDataUtil {
    fun key(): String = "test.item.key.${randomAlphabetic(1)}${randomAlphanumeric(10, 20)}".toLowerCase()

    fun label(): String = randomAlphanumeric(1, Item.LABEL_MAX_LENGTH)

    fun labelCode(): String = "test.item.label.${randomAlphabetic(1)}${randomAlphanumeric(0, 10)}".toLowerCase()

    /**
     * @return 임의의 인스턴스를 만든다.
     */
    fun random(): ItemEntity {
        return ItemEntity(key(), label(), labelCode())
    }
}
