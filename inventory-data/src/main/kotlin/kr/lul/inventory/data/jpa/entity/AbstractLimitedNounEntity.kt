package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.LimitedNounMapping.COL_LIMIT
import kr.lul.inventory.design.domain.LimitedNoun
import kr.lul.inventory.design.domain.LimitedNoun.Companion.validateLimit
import kr.lul.inventory.design.domain.Manager
import java.time.Instant
import javax.persistence.Column
import javax.persistence.MappedSuperclass

/**
 * @author justburrow
 * @since 2019-08-03
 */
@MappedSuperclass
abstract class AbstractLimitedNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String,
        limit: Int,
        description: String,
        createdAt: Instant
) : AbstractNounEntity(manager, key, label, labelCode, description, createdAt),
        LimitedNoun {
    @Column(name = COL_LIMIT)
    override var limit: Int = limit
        set(value) {
            validateLimit(value)
            field = value
        }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Any
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun toString(): String = AbstractLimitedNounEntity::class.simpleName +
            "(id=$id, manager=${manager.simpleString}, key='$key', label='$label', labelCode='$labelCode', " +
            "limit-$limit, description='$description', createdAt=$createdAt, updatedAt=$updatedAt)"
}
