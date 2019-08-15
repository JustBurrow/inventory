package kr.lul.inventory.web.manager

import com.github.bufferings.thymeleaf.extras.nl2br.dialect.Nl2brDialect
import kr.lul.inventory.business.BusinessModuleAnchor
import kr.lul.inventory.design.util.MillisecondSystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.thymeleaf.dialect.springdata.SpringDataDialect

/**
 * @author justburrow
 * @since 2019-07-10
 */
@Configuration
@ComponentScan(basePackageClasses = [BusinessModuleAnchor::class])
class WebManagerModuleConfiguration {
    @Bean
    fun timeProvider(): TimeProvider = MillisecondSystemTimeProvider()

    @Bean
    fun nl2brDialect() = Nl2brDialect()

    @Bean
    fun springDataDialect() = SpringDataDialect()
}
