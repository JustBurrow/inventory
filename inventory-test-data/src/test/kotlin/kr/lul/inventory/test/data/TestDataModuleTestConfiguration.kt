package kr.lul.inventory.test.data

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication(scanBasePackageClasses = [TestDataModuleAnchor::class, DataModuleAnchor::class])
class TestDataModuleTestConfiguration {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun managerDataUtil() = ManagerDataUtil()
}
