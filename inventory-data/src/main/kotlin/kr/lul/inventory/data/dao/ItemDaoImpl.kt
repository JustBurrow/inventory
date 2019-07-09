package kr.lul.inventory.data.dao

import kr.lul.inventory.data.jpa.entity.ItemEntity
import kr.lul.inventory.data.jpa.repository.ItemRepository
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.design.util.Assertion.`is`
import kr.lul.inventory.design.util.Assertion.zero
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
internal class ItemDaoImpl : ItemDao {
    private val log = LoggerFactory.getLogger(ItemDaoImpl::class.java)!!

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @PostConstruct
    fun postConstruct() {
        log.info("ready")
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.data.dao.ItemDao
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(item: Item): Item {
        if (log.isTraceEnabled) log.trace("args : item={}", item)

        zero(item.getId(), Item.ATTR_ID)
        `is`(item, ItemEntity::class, "item")

        val created = itemRepository.save(item as ItemEntity)

        if (log.isTraceEnabled) log.trace("return : {}", created)
        return created
    }
}
