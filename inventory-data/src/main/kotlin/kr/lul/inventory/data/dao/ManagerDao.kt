package kr.lul.inventory.data.dao

import kr.lul.inventory.design.domain.Manager

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerDao {
    fun create(manager: Manager): Manager
}
