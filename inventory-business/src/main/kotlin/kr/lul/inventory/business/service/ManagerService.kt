package kr.lul.inventory.business.service

import kr.lul.inventory.business.service.params.CreateManagerParams
import kr.lul.inventory.business.service.params.SearchCredentialParams
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.ManagerCredential

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerService {
    fun create(params: CreateManagerParams): Manager

    fun read(id: Int): Manager?

    /**
     * 조건에 맞는 [ManagerCredential]을 찾는다. 없으면 `null`.
     *
     * @return [ManagerCredential] 혹은 `null`.
     */
    fun search(params: SearchCredentialParams): ManagerCredential?
}
