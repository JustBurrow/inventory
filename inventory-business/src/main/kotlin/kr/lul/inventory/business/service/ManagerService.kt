package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.design.domain.Manager

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerService {
    fun create(params: CreateManagerParams): Manager
}
