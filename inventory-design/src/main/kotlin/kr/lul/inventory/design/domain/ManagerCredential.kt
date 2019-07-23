package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface ManagerCredential {
    companion object {
        /**
         * @see [ManagerCredential.id]
         */
        const val ATTR_ID = "id"
        /**
         * @see [ManagerCredential.manager]
         */
        const val ATTR_MANAGER = "manager"
        /**
         * @see [ManagerCredential.publicKey]
         */
        const val ATTR_PUBLIC_KEY = "publicKey"
        /**
         * @see [ManagerCredential.secretHash]
         */
        const val ATTR_SECRET_HASH = "secretHash"
        /**
         * @see [ManagerCredential.createdAt]
         */
        const val ATTR_CREATED_AT = "createdAt"

        fun isValidManager(manager: Manager) = 0 < manager.id

        @Throws(AttributeValidationException::class)
        fun validateManager(manager: Manager) {
            val msg = if (0 >= manager.id)
                "not persisted $ATTR_MANAGER : $manager"
            else
                null

            if (null != msg)
                throw AttributeValidationException(ATTR_MANAGER, manager, msg)
        }

        const val PUBLIC_KEY_MIN_LENGTH = 2
        const val PUBLIC_KEY_MAX_LENGTH = 128
        val PUBLIC_KEY_PATTERNS = listOf(Manager.NAME_PATTERN, Manager.EMAIL_PATTERN)
        val PUBLIC_KEY_REGEXS = listOf(Manager.NAME_REGEX, Manager.EMAIL_REGEX)

        fun isValidPublicKey(publicKey: String): Boolean =
                publicKey.length in PUBLIC_KEY_MIN_LENGTH..PUBLIC_KEY_MAX_LENGTH && (publicKey.matches(
                        Manager.NAME_REGEX) || publicKey.matches(Manager.EMAIL_REGEX))

        @Throws(AttributeValidationException::class)
        fun validatePublicKey(publicKey: String) {
            val msg = if (PUBLIC_KEY_MIN_LENGTH > publicKey.length)
                "too short $ATTR_PUBLIC_KEY : length=${publicKey.length}, min=$PUBLIC_KEY_MIN_LENGTH"
            else if (PUBLIC_KEY_MAX_LENGTH < publicKey.length)
                "too long $ATTR_PUBLIC_KEY : length=${publicKey.length}, max=$PUBLIC_KEY_MAX_LENGTH"
            else if (!publicKey.matches(Manager.NAME_REGEX) && !publicKey.matches(Manager.EMAIL_REGEX))
                "illegal $ATTR_PUBLIC_KEY pattern : publicKey='$publicKey', patterns=$PUBLIC_KEY_PATTERNS"
            else
                null

            if (null != msg)
                throw AttributeValidationException(ATTR_PUBLIC_KEY, publicKey, msg)
        }

        const val SECRET_HASH_PATTERN = "\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}"
        val SECRET_HASH_REGEX = SECRET_HASH_PATTERN.toRegex()

        fun isValidSecretHash(secretHash: String) = secretHash.matches(SECRET_HASH_REGEX)

        @Throws(AttributeValidationException::class)
        fun validateSecretHash(secretHash: String) {
            if (!secretHash.matches(SECRET_HASH_REGEX))
                throw AttributeValidationException(ATTR_SECRET_HASH, secretHash,
                        "illegal $ATTR_SECRET_HASH pattern : pattern='$SECRET_HASH_PATTERN'")
        }
    }

    val id: Long

    val manager: Manager

    val publicKey: String

    val secretHash: String

    val createdAt: Instant
}
