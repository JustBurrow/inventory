package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.ManagerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Repository
interface ManagerRepository : JpaRepository<ManagerEntity, Int> {
}
