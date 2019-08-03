package kr.lul.inventory.data

import kr.lul.inventory.design.util.MillisecondSystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class DataModuleTestConfiguration {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun timeProvider(): TimeProvider = MillisecondSystemTimeProvider()

    @Bean
    fun managerUtil() = ManagerUtil()

    @Bean
    fun nounUtil() = NounUtil()
}
