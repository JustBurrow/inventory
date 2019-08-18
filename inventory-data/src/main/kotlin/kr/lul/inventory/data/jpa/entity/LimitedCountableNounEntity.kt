package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.LimitedCountableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.design.domain.LimitedCountableNoun
import kr.lul.inventory.design.domain.LimitedCountableNoun.Updater
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
        LimitedCountableNoun {
    override fun updater(updatedAt: Instant): Updater {
        this.updatedAt = updatedAt

        return object : Updater {
            override val id: Int
                get() = this@LimitedCountableNounEntity.id

            override val manager: Manager
                get() = this@LimitedCountableNounEntity.manager

            override val key: String
                get() = this@LimitedCountableNounEntity.key

            override var label: String
                get() = this@LimitedCountableNounEntity.label
                set(value) {
                    this@LimitedCountableNounEntity.label = value
                }

            override var labelCode: String
                get() = this@LimitedCountableNounEntity.labelCode
                set(value) {
                    this@LimitedCountableNounEntity.labelCode = value
                }

            override var limit: Int
                get() = this@LimitedCountableNounEntity.limit
                set(value) {
                    this@LimitedCountableNounEntity.limit = value
                }

            override var description: String
                get() = this@LimitedCountableNounEntity.description
                set(value) {
                    this@LimitedCountableNounEntity.description = value
                }

            override val createdAt: Instant
                get() = this@LimitedCountableNounEntity.createdAt
            override val updatedAt: Instant
                get() = this@LimitedCountableNounEntity.updatedAt
        }
    }
}
