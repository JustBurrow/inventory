package kr.lul.inventory.business.service

import kr.lul.inventory.design.domain.Manager

/**
 * @author justburrow
 * @since 2019-08-15
 */
class NotOwnerException(
        message: String,
        val manager: Manager
) : RuntimeException(message)
