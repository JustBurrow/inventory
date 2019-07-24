package kr.lul.inventory.test

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [DataModuleAnchor::class, TestUtilModuleAnchor::class])
class TestUtilModuleTestConfiguration {
    @Bean
    fun managerUtil() = ManagerUtil()
}
