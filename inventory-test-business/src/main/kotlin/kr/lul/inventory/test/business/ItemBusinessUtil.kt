package kr.lul.inventory.test.business

import kr.lul.inventory.business.borderline.cmd.CreateItemCmd
import kr.lul.inventory.business.service.params.CreateItemParams
import kr.lul.inventory.test.data.ItemDataUtil
import org.slf4j.LoggerFactory

/**
 * @author justburrow
 * @since 2019-07-08
 */
class ItemBusinessUtil : ItemDataUtil() {
    private val log = LoggerFactory.getLogger(ItemBusinessUtil::class.java)!!

    fun createParams() = CreateItemParams(key(), label(), labelCode())

    fun createCmd() = CreateItemCmd(key(), label(), labelCode())
}
