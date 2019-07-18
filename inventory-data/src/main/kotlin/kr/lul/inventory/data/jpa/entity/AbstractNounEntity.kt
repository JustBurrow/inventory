package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.ManagerMapping
import kr.lul.inventory.data.jpa.mapping.NounMapping
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_ID
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_KEY
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_LABEL_CODE
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_MANAGER
import kr.lul.inventory.data.jpa.mapping.NounMapping.COL_TYPE
import kr.lul.inventory.data.jpa.mapping.NounMapping.ENTITY_NAME
import kr.lul.inventory.data.jpa.mapping.NounMapping.TABLE_NAME
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Noun
import kr.lul.inventory.design.domain.Noun.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Noun.Companion.validateKey
import kr.lul.inventory.design.domain.Noun.Companion.validateLabel
import kr.lul.inventory.design.domain.Noun.Companion.validateLabelCode
import kr.lul.inventory.design.domain.Noun.Companion.validateManager
import java.util.*
import javax.persistence.*

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = COL_TYPE, discriminatorType = DiscriminatorType.INTEGER)
abstract class AbstractNounEntity(
        manager: Manager,
        key: String,
        label: String,
        labelCode: String
) : Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID, nullable = false, insertable = false, updatable = false)
    private val id: Long = 0L
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
    override fun getId(): Long = id

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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // java.lang.Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun equals(other: Any?): Boolean {
        if (null == other || 0L >= id || other !is AbstractNounEntity) {
            return false
        }
        return id == other.getId()
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun toString(): String {
        return "${AbstractNounEntity::class.simpleName}(id=$id, manager=(${manager.getId()}, ${manager.getName()})," +
                " key='$key', label='$label', labelCode='$labelCode')"
    }
}
