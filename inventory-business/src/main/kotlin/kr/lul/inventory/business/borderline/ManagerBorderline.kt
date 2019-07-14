package kr.lul.inventory.business.borderline

import kr.lul.inventory.business.borderline.cmd.CreateManagerCmd
import kr.lul.inventory.dto.ManagerDetailDto
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Transactional
interface ManagerBorderline {
    fun create(cmd: CreateManagerCmd): ManagerDetailDto
}
