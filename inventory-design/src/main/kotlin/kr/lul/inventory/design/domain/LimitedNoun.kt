package kr.lul.inventory.design.domain

/**
 * @author justburrow
 * @since 2019-07-15
 */
interface LimitedNoun : Noun {
    companion object {
        const val ATTR_LIMIT = "limit"

        fun isValidLimit(limit: Int) = 0 < limit

        @Throws(AttributeValidationException::class)
        fun validateLimit(limit: Int) {
            if (0 >= limit) {
                throw AttributeValidationException("not positive limit", ATTR_LIMIT, limit)
            }
        }
    }

    interface Updater : Noun.Updater {
        var limit: Int
    }

    /**
     * @return 유저가 가질 수 있는 최대 갯수.
     */
    val limit: Int
}
