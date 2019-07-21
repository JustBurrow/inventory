package kr.lul.inventory.data

import kr.lul.inventory.test.data.ManagerDataUtil
import kr.lul.inventory.test.data.NounDataUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication(scanBasePackageClasses = [DataModuleAnchor::class])
class DataModuleTestConfiguration {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun managerDataUtil() = ManagerDataUtil()

    @Bean
    fun nounDataUtil() = NounDataUtil()
}
