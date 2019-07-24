package kr.lul.inventory.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [TestUtilModuleAnchor::class])
class TestUtilModuleTestConfiguration {
    @Bean
    fun managerUtil() = ManagerUtil()
}
