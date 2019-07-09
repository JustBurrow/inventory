package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateItemCmd
import kr.lul.inventory.dto.ItemDetailDto
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-09
 */
@Transactional
interface ItemBorderline {
    fun create(cmd: CreateItemCmd): ItemDetailDto
}
