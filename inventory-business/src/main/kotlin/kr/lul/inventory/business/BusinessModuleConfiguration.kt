package kr.lul.inventory.business

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Configuration
@ComponentScan(basePackageClasses = [DataModuleAnchor::class])
class BusinessModuleConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
