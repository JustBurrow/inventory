package kr.lul.inventory.design.domain

import java.time.Instant

/**
 * 추사화한 아이템 정보. 유저는 소유할 수 없다.
 *
 * @author justburrow
 * @since 2019-07-06
 */
interface Noun {
    companion object {
        const val ATTR_ID = "id"
        const val ATTR_MANAGER = "manager"
        const val ATTR_TYPE = "type"
        const val ATTR_KEY = "key"
        const val ATTR_LABEL = "label"
        const val ATTR_LABEL_CODE = "labelCode"
        const val ATTR_CREATED_AT = "createdAt"
        const val ATTR_UPDATED_AT = "updatedAt"

        fun isValidManager(manager: Manager) = 0 < manager.id

        @Throws(AttributeValidationException::class)
        fun validateManager(manager: Manager) {
            if (0 >= manager.id)
                throw AttributeValidationException(ATTR_MANAGER, manager,
                        "not persisted $ATTR_MANAGER : manager=$manager")
        }

        const val KEY_MIN_LENGTH = 1
        const val KEY_MAX_LENGTH: Int = 255
        const val KEY_PATTERN = "([a-z][a-z\\d]*\\.)*[a-z][a-z\\d]*"
        @JvmStatic
        val KEY_REGEX = KEY_PATTERN.toRegex()

        /**
         * 사용할 수 있는 키인지 검증한다.
         */
        @Throws(AttributeValidationException::class)
        fun validateKey(key: String) {
            val msg = if (KEY_MIN_LENGTH > key.length)
                "too short $ATTR_KEY : length=${key.length}, min=$KEY_MIN_LENGTH"
            else if (KEY_MAX_LENGTH < key.length)
                "too long $ATTR_KEY : length=${key.length}, max=$KEY_MAX_LENGTH"
            else if (!key.matches(KEY_REGEX))
                "illegal $ATTR_KEY pattern : $ATTR_KEY='$key', pattern='$KEY_PATTERN'"
            else
                null

            if (null != msg) {
                throw AttributeValidationException(ATTR_KEY, key, msg);
            }
        }

        fun isValidKey(key: String): Boolean = key.length in KEY_MIN_LENGTH..KEY_MAX_LENGTH
                && key.matches(KEY_REGEX)

        const val LABEL_MIN_LENGTH = 1
        const val LABEL_MAX_LENGTH = 255
        const val LABEL_PATTERN = "\\S(.*\\S)?"
        @JvmStatic
        val LABEL_REGEX = LABEL_PATTERN.toRegex()

        /**
         * 사용할 수 있는 레이블인지 검증한다.
         */
        @Throws(AttributeValidationException::class)
        fun validateLabel(label: String) {
            val msg = if (LABEL_MIN_LENGTH > label.length)
                "too short $ATTR_LABEL : length=${label.length}, min=$LABEL_MIN_LENGTH"
            else if (LABEL_MAX_LENGTH < label.length)
                "too long $ATTR_LABEL : length=${label.length}, max=$LABEL_MAX_LENGTH"
            else if (!label.matches(LABEL_REGEX))
                "illegal $ATTR_LABEL pattern : $ATTR_LABEL='$label', pattern='$LABEL_PATTERN'"
            else
                null

            if (null != msg) {
                throw AttributeValidationException(ATTR_LABEL, label, msg)
            }
        }

        fun isValidLabel(label: String) = label.length in LABEL_MIN_LENGTH..LABEL_MAX_LENGTH
                && label.matches(LABEL_REGEX)

        const val LABEL_CODE_MIN_LENGTH = 1
        const val LABEL_CODE_MAX_LENGTH = 255
        const val LABEL_CODE_PATTERN = "([a-z][a-z\\d]*\\.)*[a-z][a-z\\d]*"
        @JvmStatic
        val LABEL_CODE_REGEX = LABEL_CODE_PATTERN.toRegex()

        /**
         * ㅅㅏ용할 수 있는 레이블 코드인지 검증한다.
         */
        @Throws(AttributeValidationException::class)
        fun validateLabelCode(labelCode: String) {
            val msg = if (LABEL_CODE_MIN_LENGTH > labelCode.length)
                "too short $ATTR_LABEL_CODE : length=${labelCode.length}, min=$LABEL_CODE_MIN_LENGTH"
            else if (LABEL_CODE_MAX_LENGTH < labelCode.length)
                "too long $ATTR_LABEL_CODE : length=${labelCode.length}, max=$LABEL_CODE_MAX_LENGTH"
            else if (!labelCode.matches(LABEL_CODE_REGEX))
                "illegal $ATTR_LABEL_CODE pattern : $ATTR_LABEL_CODE='$labelCode', pattern='$LABEL_CODE_PATTERN'"
            else
                null

            if (null != msg) {
                throw AttributeValidationException(ATTR_LABEL_CODE, labelCode, msg)
            }
        }

        fun isValidLabelCode(labelCode: String) = labelCode.length in LABEL_CODE_MIN_LENGTH..LABEL_CODE_MAX_LENGTH
                && labelCode.matches(LABEL_REGEX)
    }

    /**
     * 시스템이 부여하는 아이템 ID.
     */
    fun getId(): Int

    /**
     * @return [Noun]의 관리자.
     */
    fun getManager(): Manager

    /**
     * @return [Noun]의 종류.
     */
    fun getType(): NounType

    /**
     * 아이템 관리자가 지정한 아이템 키. 변경할 수 없음.
     */
    fun getKey(): String

    /**
     * 아이템 사용자에게 표시할 때 사용하는 아이템의 기본 이름.
     *
     * @see getLabelCode 코드에 해당하는 레이블이 없을 경우 사용.
     */
    fun getLabel(): String

    fun setLabel(label: String)

    /**
     * 아이템 코드.
     *
     * @see https://ko.wikipedia.org/wiki/%EA%B5%AD%EC%A0%9C%ED%99%94%EC%99%80_%EC%A7%80%EC%97%AD%ED%99%94
     */
    fun getLabelCode(): String

    fun setLabelCode(labelCode: String)

    fun getCreatedAt(): Instant

    fun getUpdatedAt(): Instant
}
