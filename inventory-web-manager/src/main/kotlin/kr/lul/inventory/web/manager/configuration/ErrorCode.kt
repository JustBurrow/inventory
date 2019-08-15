package kr.lul.inventory.web.manager.configuration

/**
 * @author justburrow
 * @since 2019-07-23
 */
object ErrorCode {
    private const val PREFIX = "error"

    object ManagerErrorCode {
        private const val PREFIX = "${ErrorCode.PREFIX}.manager"

        const val CREATE_CONFIRM_NOT_MATCH = "$PREFIX.create.confirm-not-match"
        const val CREATE_UNKNOWN = "$PREFIX.create.unknown"
    }

    object NounErrorCode {
        private const val PREFIX = "${ErrorCode.PREFIX}.noun"

        const val CREATE_LIMIT_IS_NULL = "$PREFIX.create.limit.null"
        const val CREATE_LIMIT_NOT_POSITIVE = "$PREFIX.create.limit.not-positive"
        const val CREATE_LIMIT_NOT_ACCEPTABLE = "$PREFIX.create.limit.not-acceptable"
    }
}
