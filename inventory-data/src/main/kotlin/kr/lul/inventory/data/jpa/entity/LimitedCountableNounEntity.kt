package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.LimitedCountableNounMapping.COL_LIMIT
import kr.lul.inventory.data.jpa.mapping.LimitedCountableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

/**
 * @author justburrow
 * @since 2019-07-18
 */
@Entity
@DiscriminatorValue(DISCRIMINATOR_VALUE)
class LimitedCountableNounEntity(
        manager: Manager, key: String, label: String, labelCode: String,
        @Column(name = COL_LIMIT, nullable = false)
        private var limit: Int,
        createdAt: Instant
) : AbstractNounEntity(manager, key, label, labelCode, createdAt), LimitedCountableNoun {
    override fun getLimit(): Int = limit
}
