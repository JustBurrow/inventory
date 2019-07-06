package kr.lul.inventory.data.jpa.repository

import kr.lul.inventory.data.jpa.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Repository
interface ItemRepository : JpaRepository<ItemEntity, Long>
