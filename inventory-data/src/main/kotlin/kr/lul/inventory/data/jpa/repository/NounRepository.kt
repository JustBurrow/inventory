package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.AbstractNounEntity
import kr.lul.inventory.design.domain.Manager
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Repository
interface NounRepository : JpaRepository<AbstractNounEntity, Int> {
    fun existsByKey(key: String): Boolean

    fun existsByLabel(label: String): Boolean

    fun existsByLabelCode(labelCode: String): Boolean

    fun findAllByManager(manager: Manager, page: Pageable): Page<AbstractNounEntity>
}
