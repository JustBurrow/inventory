package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.design.domain.Item
import kr.lul.inventory.design.domain.Item.Companion.KEY_MAX_LENGTH
import kr.lul.inventory.design.domain.Item.Companion.validateKey
import kr.lul.inventory.design.domain.Item.Companion.validateLabel
import kr.lul.inventory.design.domain.Item.Companion.validateLabelCode
import java.util.*
import javax.persistence.*

/**
 * @author justburrow
 * @since 2019-07-06
 */
@Entity(name = "Item")
@Table(name = "master_item")
class ItemEntity(key: String, label: String, labelCode: String) : Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private val id: Long = 0L
    @Column(name = "key", length = KEY_MAX_LENGTH, nullable = false, updatable = false)
    private val key: String
    @Column(name = "label", nullable = false)
    private lateinit var label: String
    @Column(name = "label_code", nullable = false)
    private lateinit var labelCode: String

    init {
        validateKey(key)

        this.key = key
        setLabel(label)
        setLabelCode(labelCode)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.Item
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun getId(): Long = id

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
        if (null == other || 0L >= id || other !is ItemEntity) {
            return false
        }
        return id == other.getId()
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun toString(): String {
        return "ItemEntity(id=$id, key='$key', label='$label', labelCode='$labelCode')"
    }
}
