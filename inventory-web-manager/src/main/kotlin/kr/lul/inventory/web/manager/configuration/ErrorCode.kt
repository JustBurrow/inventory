package kr.lul.inventory.web.manager.configuration

/**
 * @author justburrow
 * @since 2019-07-23
 */
object ErrorCode {
    private const val PREFIX = "error"

    object ManagerErrorCode {
        const val PREFIX = "${ErrorCode.PREFIX}.manager"

        const val CREATE_CONFIRM_NOT_MATCH = "$PREFIX.create.confirm-not-match"
        const val CREATE_UNKNOWN = "$PREFIX.create.unknown"
    }
}
