package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.LimitedCountableNounEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-21
 */
@Repository
interface LimitedCountableNounRepository : JpaRepository<LimitedCountableNounEntity, Int>
