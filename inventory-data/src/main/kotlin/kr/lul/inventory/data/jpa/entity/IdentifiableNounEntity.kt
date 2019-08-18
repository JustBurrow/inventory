package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.IdentifiableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.data.jpa.mapping.IdentifiableNounMapping.ENTITY_NAME
import kr.lul.inventory.design.domain.IdentifiableNoun
import kr.lul.inventory.design.domain.IdentifiableNoun.Updater
import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

/**
 * @author justburrow
 * @since 2019-07-15
 */
@Entity(name = ENTITY_NAME)
@DiscriminatorValue(DISCRIMINATOR_VALUE)
class IdentifiableNounEntity(
        manager: Manager, key: String, label: String, labelCode: String, description: String, createdAt: Instant
) : AbstractNounEntity(manager, key, label, labelCode, description, createdAt),
        IdentifiableNoun {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.IdentifiableNoun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun updater(updatedAt: Instant): Updater {
        this.updatedAt = updatedAt

        return object : Updater {
            override val id: Int
                get() = this@IdentifiableNounEntity.id

            override val manager: Manager
                get() = this@IdentifiableNounEntity.manager

            override val key: String
                get() = this@IdentifiableNounEntity.key

            override var label: String
                get() = this@IdentifiableNounEntity.label
                set(value) {
                    this@IdentifiableNounEntity.label = value
                }

            override var labelCode: String
                get() = this@IdentifiableNounEntity.labelCode
                set(value) {
                    this@IdentifiableNounEntity.labelCode = value
                }

            override var description: String
                get() = this@IdentifiableNounEntity.description
                set(value) {
                    this@IdentifiableNounEntity.description = value
                }
            override val createdAt: Instant
                get() = this@IdentifiableNounEntity.createdAt

            override val updatedAt: Instant
                get() = this@IdentifiableNounEntity.updatedAt
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kotlin.Any
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun toString(): String = IdentifiableNounEntity::class.simpleName +
            "(id=$id, manager=${manager.simpleString}, key='$key', label='$label', labelCode='$labelCode', " +
            "description='$description', createdAt=$createdAt, updatedAt=$updatedAt)"
}
