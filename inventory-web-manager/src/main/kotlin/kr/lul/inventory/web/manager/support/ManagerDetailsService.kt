package kr.lul.inventory.web.manager.support

import kr.lul.inventory.web.manager.controller.argument.CurrentManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Transactional
interface ManagerDetailsService : UserDetailsService {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.springframework.security.core.userdetails.UserDetailsService
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun loadUserByUsername(username: String?): CurrentManager
}
