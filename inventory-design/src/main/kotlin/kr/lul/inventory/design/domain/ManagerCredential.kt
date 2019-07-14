package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerCredential {
    companion object {
        const val ATTR_ID = "id"
        const val ATTR_MANAGER = "manager"
        const val ATTR_PUBLIC_KEY = "publicKey"
        const val ATTR_SECRET_HASH = "secretHash"
        const val ATTR_CREATED_AT = "createdAt"
    }

    fun getId(): Long

    fun getManager(): Manager

    fun getPublicKey(): String

    fun getSecretHash(): String

    fun getCreatedAt(): Instant
}
