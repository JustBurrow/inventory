package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateItemParams
import kr.lul.inventory.data.dao.ItemDao
import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.design.factory.ItemFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Service
internal class ItemServiceImpl : ItemService {
    private val log = LoggerFactory.getLogger(ItemServiceImpl::class.java)!!

    @Autowired
    private lateinit var itemFactory: ItemFactory
    @Autowired
    private lateinit var itemDao: ItemDao

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.service.ItemService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(params: CreateItemParams): Item {
        if (log.isTraceEnabled) log.trace("args : params={}", params)

        var item = itemFactory.getInstance(params.key, params.label, params.labelCode)
        item = itemDao.create(item)

        if (log.isTraceEnabled) log.trace("return : {}", item)
        return item
    }
}
