package kr.lul.inventory.web.manager.support

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Transactional
interface ManagerDetailsService : UserDetailsService {
    override fun loadUserByUsername(username: String?): ManagerDetails
}
