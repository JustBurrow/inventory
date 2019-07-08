package kr.lul.inventory.business

import kr.lul.inventory.test.business.ItemBusinessUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [BusinessModuleAnchor::class])
class BusinessModuleTestConfiguration {
    @Bean
    fun testUtil() = ItemBusinessUtil()
}
