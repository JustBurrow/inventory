package kr.lul.inventory.business

import kr.lul.inventory.test.business.ManagerBusinessUtl
import kr.lul.inventory.test.business.NounBusinessUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [BusinessModuleAnchor::class])
class BusinessModuleTestConfiguration {
    @Bean
    fun nounBusinessUtil() = NounBusinessUtil()

    @Bean
    fun managerBusinessUtl() = ManagerBusinessUtl()
}
