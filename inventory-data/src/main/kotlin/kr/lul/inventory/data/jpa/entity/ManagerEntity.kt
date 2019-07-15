package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_CREATED_AT
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_EMAIL
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_ID
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_NAME
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.COL_UPDATED_AT
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.ENTITY
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.TABLE
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.UQ_MANAGER_EMAIL
import kr.lul.inventory.data.jpa.mapping.ManagerMapping.UQ_MANAGER_NAME
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.Manager.Companion.validateEmail
import kr.lul.inventory.design.domain.Manager.Companion.validateName
import java.time.Instant
import javax.persistence.*

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Entity(name = ENTITY)
@Table(name = TABLE,
        uniqueConstraints = [UniqueConstraint(name = UQ_MANAGER_EMAIL, columnNames = [COL_EMAIL]),
            UniqueConstraint(name = UQ_MANAGER_NAME, columnNames = [COL_NAME])])
class ManagerEntity(email: String, name: String, createdAt: Instant) : Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID, nullable = false, insertable = false, updatable = false)
    private var id: Int = 0
    @Column(name = COL_EMAIL, unique = true, nullable = false)
    private lateinit var email: String
    @Column(name = COL_NAME, unique = true, nullable = false)
    private lateinit var name: String
    @Column(name = COL_CREATED_AT, nullable = false, updatable = false)
    private lateinit var createdAt: Instant
    @Column(name = COL_UPDATED_AT, nullable = false)
    private lateinit var updatedAt: Instant

    init {
        validateEmail(email)
        validateName(name)

        this.email = email
        this.name = name
        this.createdAt = createdAt
        this.updatedAt = createdAt
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // kr.lul.inventory.design.domain.Manager
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun getId(): Int = id

    override fun getEmail(): String = email

    override fun getName(): String = name

    override fun getCreatedAt(): Instant = createdAt

    override fun getUpdatedAt(): Instant = updatedAt

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // java.lang.Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (0 >= id || other !is ManagerEntity) return false

        return id != other.id
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "${ManagerEntity::class.simpleName}(id=$id, email='$email', name='$name', createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
