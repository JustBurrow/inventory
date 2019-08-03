package kr.lul.inventory.design.domain

import kr.lul.inventory.design.util.ToSimpleString
import java.time.Instant

/**
 * @author justburrow
 * @since 2019-07-14
 */
interface Manager : ToSimpleString {
    companion object {
        /**
         * @see [Manager.id]
         */
        const val ATTR_ID = "id"
        /**
         * @see [Manager.email]
         */
        const val ATTR_EMAIL = "email"
        /**
         * @see [Manager.name]
         */
        const val ATTR_NAME = "name"
        const val ATTR_PASSWORD = "password"
        /**
         * @see [Manager.createdAt]
         */
        const val ATTR_CREATED_AT = "createdAt"
        /**
         * @see [Manager.updatedAt]
         */
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

        const val PASSWORD_MIN_LENGTH = 4

        fun isValidPassword(password: String) = PASSWORD_MIN_LENGTH < password.length

        @Throws(AttributeValidationException::class)
        fun validatePassword(secret: String) {
            if (PASSWORD_MIN_LENGTH > secret.length)
                throw AttributeValidationException(ATTR_PASSWORD, "[ PROTECTED ]",
                        "too short $ATTR_PASSWORD : length=${secret.length}, min=$PASSWORD_MIN_LENGTH")
        }
    }

    val id: Int

    val email: String

    val name: String

    val createdAt: Instant

    val updatedAt: Instant
}
