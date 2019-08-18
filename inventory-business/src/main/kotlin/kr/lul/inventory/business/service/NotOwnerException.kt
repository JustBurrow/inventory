package kr.lul.inventory.business.service

import kr.lul.inventory.design.domain.Manager

/**
 * @author justburrow
 * @since 2019-08-15
 */
class NotOwnerException(
        message: String,
        /**
         * 로직 실행을 요청한 관리자.
         */
        val manager: Manager
) : RuntimeException(message)
