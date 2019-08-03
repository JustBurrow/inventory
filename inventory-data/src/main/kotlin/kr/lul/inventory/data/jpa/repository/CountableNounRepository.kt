package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.CountableNounEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-21
 */
@Repository
interface CountableNounRepository : JpaRepository<CountableNounEntity, Int>
