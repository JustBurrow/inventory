package kr.lul.inventory.data

import kr.lul.inventory.test.data.ItemTestUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackageClasses = [DataModuleAnchor::class])
class DataModuleTestConfiguration {
    @Bean
    fun itemUtil() = ItemTestUtil()
}
