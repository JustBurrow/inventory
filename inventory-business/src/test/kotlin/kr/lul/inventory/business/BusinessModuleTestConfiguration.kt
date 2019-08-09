package kr.lul.inventory.business

import kr.lul.inventory.design.util.MillisecondSystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.test.ManagerUtil
import kr.lul.inventory.test.NounUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BusinessModuleTestConfiguration {
    @Bean
    fun timeProvider(): TimeProvider = MillisecondSystemTimeProvider()

    @Bean
    fun managerUtil() = ManagerUtil()

    @Bean
    fun nounUtil() = NounUtil()
}
