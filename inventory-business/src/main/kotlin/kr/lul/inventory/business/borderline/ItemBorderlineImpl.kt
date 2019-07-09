package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateItemCmd
import kr.lul.inventory.business.converter.ItemConverter
import kr.lul.inventory.business.service.ItemService
import kr.lul.inventory.business.service.params.CreateItemParams
import kr.lul.inventory.dto.ItemDetailDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class ItemBorderlineImpl : ItemBorderline {
    private val log = LoggerFactory.getLogger(ItemBorderlineImpl::class.java)!!

    @Autowired
    private lateinit var itemService: ItemService
    @Autowired
    private lateinit var itemConverter: ItemConverter

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.business.borderline.ItemBorderline
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun create(cmd: CreateItemCmd): ItemDetailDto {
        if (log.isTraceEnabled) log.trace("args : cmd={}", cmd)

        val item = itemService.create(CreateItemParams(cmd.key, cmd.label, cmd.labelCode))
        val dto = itemConverter.convert(item, ItemDetailDto::class)

        if (log.isTraceEnabled) log.trace("return : {}", dto)
        return dto
    }
}
