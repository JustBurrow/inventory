package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.ManagerMapping
import kr.lul.inventory.data.jpa.mapping.NounMapping
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_CREATED_AT
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_ID
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_KEY
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL_CODE
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_MANAGER
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_TYPE
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_UPDATED_AT
import kr.lul.inventory.data.jpa.mapping.NounMapping.ENTITY_NAME
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_MANAGER
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_MANAGER_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_NOUN_TYPE
import kr.lul.inventory.data.jpa.mapping.NounMapping.FK_NOUN_PK_NOUN_TYPE_COLUMNS
import kr.lul.inventory.data.jpa.mapping.NounMapping.TABLE_NAME
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
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
        indexes = [Index(name = FK_NOUN_PK_MANAGER, columnList = FK_NOUN_PK_MANAGER_COLUMNS),
            Index(name = FK_NOUN_PK_NOUN_TYPE, columnList = FK_NOUN_PK_NOUN_TYPE_COLUMNS)])
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = COL_TYPE, discriminatorType = DiscriminatorType.INTEGER)
abstract class AbstractNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String,
        createdAt: Instant
) : Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID, nullable = false, insertable = false, updatable = false)
    private val id: Int = 0
    @ManyToOne(targetEntity = ManagerEntity::class)
    @JoinColumn(name = COL_MANAGER, nullable = false, updatable = false,
            foreignKey = ForeignKey(name = NounMapping.FK_NOUN_PK_MANAGER),
            referencedColumnName = ManagerMapping.COL_ID)
    private val manager: Manager
    @Column(name = COL_KEY, length = KEY_MAX_LENGTH, nullable = false, updatable = false)
    private val key: String
    @Column(name = COL_LABEL, nullable = false)
    private lateinit var label: String
    @Column(name = COL_LABEL_CODE, nullable = false)
    private lateinit var labelCode: String
    @Column(name = COL_CREATED_AT, nullable = false, updatable = false)
    private var createdAt: Instant = createdAt
    @Column(name = COL_UPDATED_AT, nullable = false)
    private var updatedAt: Instant = createdAt

    init {
        validateManager(manager)
        validateKey(key)

        this.manager = manager
        this.key = key
        setLabel(label)
        setLabelCode(labelCode)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.Noun
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun getId(): Int = id

    override fun getManager(): Manager = manager

    override fun getKey(): String = key

    override fun getLabel(): String = label

    override fun setLabel(label: String) {
        validateLabel(label)
        this.label = label
    }

    override fun getLabelCode(): String = labelCode

    override fun setLabelCode(labelCode: String) {
        validateLabelCode(labelCode)
        this.labelCode = labelCode
    }

    override fun getCreatedAt(): Instant = createdAt

    override fun getUpdatedAt(): Instant = updatedAt

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // java.lang.Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun hashCode(): Int {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (0 >= id || other !is AbstractNounEntity) return false

        return id == other.id
    }
}
