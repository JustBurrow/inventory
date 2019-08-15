package kr.lul.inventory.web.manager.controller.argument

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.time.ZonedDateTime

class CurrentManager(
        val id: Int,
        val email: String,
        val name: String,
        val createdAt: ZonedDateTime,
        val updatedAt: ZonedDateTime,
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
    override fun toString() = "${CurrentManager::class.simpleName}(id=$id, email='$email', name='$name', " +
            "enabled=$isEnabled, accountNonExpired=$isAccountNonExpired, " +
            "credentialsNonExpired=$isCredentialsNonExpired, accountNonLocked=$isAccountNonLocked " +
            "createdAt=$createdAt, updatedAt=$updatedAt)"
}
