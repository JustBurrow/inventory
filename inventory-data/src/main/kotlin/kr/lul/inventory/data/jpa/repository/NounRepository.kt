package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.AbstractNounEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Repository
interface NounRepository : JpaRepository<AbstractNounEntity, Int> {
    fun existsByKey(key: String): Boolean
}
