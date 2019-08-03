package kr.lul.inventory.data.jpa.entity

import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.COL_CREATED_AT
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.COL_ID
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.COL_MANAGER
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.COL_PUBLIC_KEY
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.COL_SECRET_HASH
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.ENTITY
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.FK_MANAGER_CREDENTIAL_PK_MANAGER
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.TABLE
import kr.lul.inventory.data.jpa.mapping.ManagerCredentialMapping.UQ_MANAGER_CREDENTIAL_PUBLIC_KEY
import kr.lul.inventory.data.jpa.mapping.ManagerMapping
import kr.lul.inventory.design.domain.Manager
import kr.lul.inventory.design.domain.ManagerCredential
import kr.lul.inventory.design.domain.ManagerCredential.Companion.validateManager
import kr.lul.inventory.design.domain.ManagerCredential.Companion.validatePublicKey
import kr.lul.inventory.design.domain.ManagerCredential.Companion.validateSecretHash
import java.time.Instant
import javax.persistence.*

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Entity(name = ENTITY)
@Table(name = TABLE,
        uniqueConstraints = [UniqueConstraint(name = UQ_MANAGER_CREDENTIAL_PUBLIC_KEY, columnNames = [COL_PUBLIC_KEY])])
class ManagerCredentialEntity(
        @ManyToOne(targetEntity = ManagerEntity::class)
        @JoinColumn(name = COL_MANAGER, nullable = false, updatable = false,
                foreignKey = ForeignKey(name = FK_MANAGER_CREDENTIAL_PK_MANAGER),
                referencedColumnName = ManagerMapping.COL_ID)
        override val manager: Manager,
        @Column(name = COL_PUBLIC_KEY, nullable = false, updatable = false)
        override val publicKey: String,
        @Column(name = COL_SECRET_HASH, nullable = false, updatable = false)
        override val secretHash: String,
        @Column(name = COL_CREATED_AT, nullable = false, updatable = false)
        override val createdAt: Instant
) : ManagerCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COL_ID, nullable = false, insertable = false, updatable = false)
    override val id: Long = 0L

    init {
        validateManager(manager)
        validatePublicKey(publicKey)
        validateSecretHash(secretHash)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // java.lang.Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (0L >= id || other !is ManagerCredentialEntity) return false

        return id == other.id
    }

    override fun toString(): String = "${ManagerCredentialEntity::class.simpleName}" +
            "(id=$id, manager=${manager.toSimpleString()}, " +
            "publicKey='$publicKey', secretHash=[ PROTECTED ], createdAt=$createdAt)"
}
