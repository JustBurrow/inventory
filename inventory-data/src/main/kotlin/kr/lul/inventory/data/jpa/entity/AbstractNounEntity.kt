package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.ManagerMapping
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_CREATED_AT
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_DESCRIPTION
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_ID
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL_CODE
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_MANAGER
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_NOUN_KEY
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_NOUN_TYPE
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_UPDATED_AT
import kr.lul.inventory.data.jpa.mapping.NounMapping.ENTITY_NAME
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_MANAGER
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_MANAGER_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_NOUN_TYPE
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_NOUN_TYPE_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.IDX_NOUN_LABEL
import kr.lul.inventory.data.jpa.mapping.NounMapping.IDX_NOUN_LABEL_CODE
import kr.lul.inventory.data.jpa.mapping.NounMapping.IDX_NOUN_LABEL_CODE_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.IDX_NOUN_LABEL_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.TABLE_NAME
import kr.lul.inventory.data.jpa.mapping.NounMapping.UQ_NOUN_KEY
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.DESCRIPTION_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.validateDescription
import kr.lul.inventory.design.domain.Noun.Companion.validateKey
import kr.lul.inventory.design.domain.Noun.Companion.validateLabel
import kr.lul.inventory.design.domain.Noun.Companion.validateLabelCode
import kr.lul.inventory.design.domain.Noun.Companion.validateManager
import java.time.Instant
import javax.persistence.*

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME,
        uniqueConstraints = [UniqueConstraint(name = UQ_NOUN_KEY, columnNames = [COL_NOUN_KEY])],
        indexes = [Index(name = FK_NOUN_PK_MANAGER, columnList = FK_NOUN_PK_MANAGER_COLUMNS),
            Index(name = FK_NOUN_PK_NOUN_TYPE, columnList = FK_NOUN_PK_NOUN_TYPE_COLUMNS),
            Index(name = IDX_NOUN_LABEL, columnList = IDX_NOUN_LABEL_COLUMNS),
            Index(name = IDX_NOUN_LABEL_CODE, columnList = IDX_NOUN_LABEL_CODE_COLUMNS)])
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = COL_NOUN_TYPE, discriminatorType = DiscriminatorType.INTEGER)
abstract class AbstractNounEntity(
        @ManyToOne(targetEntity = ManagerEntity::class)
        @JoinColumn(name = COL_MANAGER, nullable = false, updatable = false,
                foreignKey = ForeignKey(name = FK_NOUN_PK_MANAGER),
                referencedColumnName = ManagerMapping.COL_ID)
        override val manager: Manager,
        @Column(name = COL_NOUN_KEY, length = KEY_MAX_LENGTH, nullable = false, unique = true, updatable = false)
        override val key: String,
        label: String,
        labelCode: String,
        description: String,
        @Column(name = COL_CREATED_AT, nullable = false, updatable = false)
        override val createdAt: Instant
) : Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID, nullable = false, insertable = false, updatable = false)
    override val id: Int = 0
    @Column(name = COL_LABEL, nullable = false)
    override var label: String = label
        set(value) {
            validateLabel(value)
            field = value
        }
    @Column(name = COL_LABEL_CODE, nullable = false)
    override var labelCode: String = labelCode
        set(value) {
            validateLabelCode(value)
            field = value
        }
    @Column(name = COL_DESCRIPTION, length = DESCRIPTION_MAX_LENGTH, nullable = false)
    override var description: String = description
        set(value) {
            validateDescription(value)
            field = value
        }
    @Column(name = COL_UPDATED_AT, nullable = false)
    override var updatedAt: Instant = createdAt

    init {
        validateManager(manager)
        validateKey(key)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kotlin.Any
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun hashCode() = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (0 >= id || other !is AbstractNounEntity) return false

        return id == other.id
    }

    override fun toString(): String = AbstractNounEntity::class.simpleName +
            "(id=$id, manager=${manager.simpleString}, key='$key', label='$label', labelCode='$labelCode', " +
            "description='$description', createdAt=$createdAt, updatedAt=$updatedAt)"
}
