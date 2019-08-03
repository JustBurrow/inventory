package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.LimitedCountableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

/**
 * @author justburrow
 * @since 2019-07-18
 */
@Entity
@DiscriminatorValue(DISCRIMINATOR_VALUE)
class LimitedCountableNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String,
        limit: Int,
        description: String,
        createdAt: Instant
) : AbstractLimitedNounEntity(manager, key, label, labelCode, limit, description, createdAt),
        LimitedCountableNoun
