package kr.lul.inventory.business

import kr.lul.inventory.test.ManagerUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BusinessModuleTestConfiguration {
    @Bean
    fun managerUtil() = ManagerUtil()
}
