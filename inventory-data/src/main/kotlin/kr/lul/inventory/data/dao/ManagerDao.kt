package kr.lul.inventory.data.dao

import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.ManagerCredential

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerDao {
    fun create(manager: Manager): Manager

    fun create(credential: ManagerCredential): ManagerCredential

    fun read(id: Int): Manager?

    fun readCredential(publicKey: String): ManagerCredential?
}
