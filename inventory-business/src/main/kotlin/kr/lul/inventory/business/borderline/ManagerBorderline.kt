package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.business.borderline.cmd.ReadManagerCmd
import kr.lul.inventory.dto.ManagerDetailDto

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerBorderline {
    fun create(cmd: CreateManagerCmd): ManagerDetailDto

    fun read(cmd: ReadManagerCmd): ManagerDetailDto?
}
