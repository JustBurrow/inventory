package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.dto.NounDetailDto
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-09
 */
@Transactional
interface NounBorderline {
    fun create(cmd: CreateNounCmd): NounDetailDto
}
