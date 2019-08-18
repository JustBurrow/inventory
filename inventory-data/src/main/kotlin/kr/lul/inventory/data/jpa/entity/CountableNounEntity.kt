package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.CountableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.data.jpa.mapping.CountableNounMapping.ENTITY_NAME
import kr.lul.inventory.design.domain.CountableNoun
import kr.lul.inventory.design.domain.CountableNoun.Updater
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
class CountableNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String,
        description: String,
        createdAt: Instant
) : AbstractNounEntity(manager, key, label, labelCode, description, createdAt),
        CountableNoun {
    override fun updater(updatedAt: Instant): Updater {
        this.updatedAt = updatedAt

        return object : Updater {
            override val id: Int
                get() = this@CountableNounEntity.id

            override val manager: Manager
                get() = this@CountableNounEntity.manager

            override val key: String
                get() = this@CountableNounEntity.key

            override var label: String
                get() = this@CountableNounEntity.label
                set(value) {
                    this@CountableNounEntity.label = value
                }

            override var labelCode: String
                get() = this@CountableNounEntity.labelCode
                set(value) {
                    this@CountableNounEntity.labelCode = value
                }

            override var description: String
                get() = this@CountableNounEntity.description
                set(value) {
                    this@CountableNounEntity.description = value
                }

            override val createdAt: Instant
                get() = this@CountableNounEntity.createdAt

            override val updatedAt: Instant
                get() = this@CountableNounEntity.updatedAt
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Any
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun toString(): String = CountableNounEntity::class.simpleName +
            "(id=$id, manager=${manager.simpleString}, key='$key', label='$label', labelCode='$labelCode'," +
            " description='$description', createdAt=$createdAt, updatedAt=$updatedAt)"
}
