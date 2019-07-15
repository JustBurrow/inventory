package kr.lul.inventory.design.domain

import kr.lul.inventory.design.domain.Constants.Role.ROLE_ANONYMOUS
import kr.lul.inventory.design.domain.Constants.Role.ROLE_MANAGER

/**
 * @author justburrow
 * @since 2019-07-15
 */
enum class UserRoles(val role: String) {
    ANONYMOUS(ROLE_ANONYMOUS),
    MANAGER(ROLE_MANAGER);
}
