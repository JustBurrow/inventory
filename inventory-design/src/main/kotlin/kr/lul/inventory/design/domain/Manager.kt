package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface Manager {
    companion object {
        const val ATTR_ID = "id"
        const val ATTR_EMAIL = "email"
        const val ATTR_NAME = "name"
        const val ATTR_CREATED_AT = "createdAt"
        const val ATTR_UPDATED_AT = "updatedAt"

        const val EMAIL_MAX_LENGTH = 128
        const val EMAIL_PATTERN = "^[a-z\\d._.%+-]+@([a-z\\d.-]+\\.)*[a-z]{2,6}$"
        val EMAIL_REGEX = EMAIL_PATTERN.toRegex()

        fun isValidEmail(email: String) = email.length in 1..EMAIL_MAX_LENGTH
                && email.matches(EMAIL_REGEX)

        @Throws(AttributeValidationException::class)
        fun validateEmail(email: String) {
            val msg = if (email.isEmpty())
                "$ATTR_EMAIL is empty."
            else if (EMAIL_MAX_LENGTH < email.length)
                "$ATTR_EMAIL is too long : length=${email.length}, max=$EMAIL_MAX_LENGTH"
            else if (!email.matches(EMAIL_REGEX))
                "illegal $ATTR_EMAIL pattern : email='$email', pattern='$EMAIL_PATTERN'"
            else
                null

            if (null != msg) {
                throw AttributeValidationException(ATTR_EMAIL, email, msg)
            }
        }

        const val NAME_MIN_LENGTH = 2
        const val NAME_MAX_LENGTH = 64
        const val NAME_PATTERN = "\\S(.*\\S)?"
        val NAME_REGEX = NAME_PATTERN.toRegex()

        fun isValidName(name: String) = name.length in NAME_MIN_LENGTH..NAME_MAX_LENGTH
                && name.matches(NAME_REGEX)

        @Throws(AttributeValidationException::class)
        fun validateName(name: String) {
            val msg = if (NAME_MIN_LENGTH > name.length)
                "too short $ATTR_NAME : length=${name.length}, min=$NAME_MIN_LENGTH"
            else if (NAME_MAX_LENGTH < name.length)
                "too long $ATTR_NAME : length=${name.length}, max=$NAME_MAX_LENGTH"
            else if (!name.matches(NAME_REGEX))
                "illegal $ATTR_NAME pattern : name='$name', pattern='$NAME_PATTERN'"
            else
                null

            if (null != msg) {
                throw AttributeValidationException(ATTR_NAME, name, msg)
            }
        }
    }

    fun getId(): Int

    fun getEmail(): String

    fun getName(): String

    fun getCreatedAt(): Instant

    fun getUpdatedAt(): Instant
}
