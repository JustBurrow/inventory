package kr.lul.inventory.test.data

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [TestDataModuleAnchor::class, DataModuleAnchor::class])
class TestDataModuleTestConfiguration {
    @Bean
    fun nounDataUtil() = NounDataUtil()
}
