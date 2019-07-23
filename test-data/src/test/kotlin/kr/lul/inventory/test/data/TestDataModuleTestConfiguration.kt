package kr.lul.inventory.test.data

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [TestDataModuleAnchor::class])
class TestDataModuleTestConfiguration {
    @Bean
    fun managerDataUtil() = ManagerDataUtil()
}
