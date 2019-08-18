package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.LimitedIdentifiableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.design.domain.LimitedIdentifiableNoun
import kr.lul.inventory.design.domain.LimitedIdentifiableNoun.Updater
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
class LimitedIdentifiableNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String,
        limit: Int,
        description: String,
        createdAt: Instant
) : AbstractLimitedNounEntity(manager, key, label, labelCode, limit, description, createdAt),
        LimitedIdentifiableNoun {
    override fun updater(updatedAt: Instant): Updater {
        this.updatedAt = updatedAt
        return object : Updater {
            override val id: Int
                get() = this@LimitedIdentifiableNounEntity.id

            override val manager: Manager
                get() = this@LimitedIdentifiableNounEntity.manager

            override val key: String
                get() = this@LimitedIdentifiableNounEntity.key

            override var label: String
                get() = this@LimitedIdentifiableNounEntity.label
                set(value) {
                    this@LimitedIdentifiableNounEntity.label = value
                }

            override var labelCode: String
                get() = this@LimitedIdentifiableNounEntity.labelCode
                set(value) {
                    this@LimitedIdentifiableNounEntity.labelCode = value
                }

            override var limit: Int
                get() = this@LimitedIdentifiableNounEntity.limit
                set(value) {
                    this@LimitedIdentifiableNounEntity.limit = value
                }

            override var description: String
                get() = this@LimitedIdentifiableNounEntity.description
                set(value) {
                    this@LimitedIdentifiableNounEntity.description = value
                }

            override val createdAt: Instant
                get() = this@LimitedIdentifiableNounEntity.createdAt

            override val updatedAt: Instant
                get() = this@LimitedIdentifiableNounEntity.updatedAt
        }
    }
}
