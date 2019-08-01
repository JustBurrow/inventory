package kr.lul.inventory.test

import kr.lul.inventory.data.DataModuleAnchor
import kr.lul.inventory.design.util.SystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [DataModuleAnchor::class])
class TestUtilModuleTestConfiguration {
    @Bean
    fun timeProvider(): TimeProvider = SystemTimeProvider()

    @Bean
    fun managerUtil() = ManagerUtil()
}
