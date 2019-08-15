package kr.lul.inventory.web.manager.exception

/**
 * @author justburrow
 * @since 2019-08-15
 */
class NotFoundException(
        message: String,
        cause: Throwable?
) : RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
