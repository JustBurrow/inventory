package kr.lul.inventory.web.manager.controller.argument

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.time.ZonedDateTime

class CurrentManager(
        private val id: Int,
        private val email: String,
        private val name: String,
        private val createdAt: ZonedDateTime,
        private val updatedAt: ZonedDateTime,
        password: String
) : User(name, password, true, true, true, true,
        mutableListOf(SimpleGrantedAuthority(
                ROLE_MANAGER))) {
    companion object {
        const val ROLE_MANAGER = "ROLE_MANAGER"
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // java.lang.Object
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun toString(): String {
        return "${CurrentManager::class.simpleName}(id=$id, email='$email', name='$name', " +
                "enabled=$isEnabled, accountNonExpired=$isAccountNonExpired, " +
                "credentialsNonExpired=$isCredentialsNonExpired, accountNonLocked=$isAccountNonLocked" +
                "createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
