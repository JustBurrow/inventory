package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateNounCmd
import kr.lul.inventory.dto.NounDetailDto

/**
 * @author justburrow
 * @since 2019-07-09
 */
interface NounBorderline {
    fun create(cmd: CreateNounCmd): NounDetailDto
}
