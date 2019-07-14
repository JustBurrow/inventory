package kr.lul.inventory.data

import kr.lul.inventory.test.data.ManagerDataUtil
import kr.lul.inventory.test.data.NounDataUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [DataModuleAnchor::class])
class DataModuleTestConfiguration {
    @Bean
    fun nounDataUtil() = NounDataUtil()

    @Bean
    fun managerDataUtil() = ManagerDataUtil()
}
