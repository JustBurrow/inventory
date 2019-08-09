package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.IdentifiableNounMapping.DISCRIMINATOR_VALUE
import kr.lul.inventory.data.jpa.mapping.IdentifiableNounMapping.ENTITY_NAME
import kr.lul.inventory.design.domain.IdentifiableNoun
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
    override fun toString(): String = IdentifiableNounEntity::class.simpleName +
            "(id=$id, manager=${manager.simpleString}, key='$key', label='$label', labelCode='$labelCode', " +
            "description='$description', createdAt=$createdAt, updatedAt=$updatedAt)"
}
