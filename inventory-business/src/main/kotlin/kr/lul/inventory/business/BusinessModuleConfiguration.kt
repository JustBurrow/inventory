package kr.lul.inventory.business

import kr.lul.inventory.data.DataModuleAnchor
import kr.lul.inventory.design.util.SystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Configuration
@ComponentScan(basePackageClasses = [DataModuleAnchor::class])
class BusinessModuleConfiguration {
    @Bean
    fun timeProvider(): TimeProvider = SystemTimeProvider()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
