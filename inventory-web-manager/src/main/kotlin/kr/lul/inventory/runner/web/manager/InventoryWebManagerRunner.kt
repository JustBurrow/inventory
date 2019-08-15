package kr.lul.inventory.runner.web.manager

import com.github.bufferings.thymeleaf.extras.nl2br.dialect.Nl2brDialect
import kr.lul.inventory.design.util.MillisecondSystemTimeProvider
import kr.lul.inventory.design.util.TimeProvider
import kr.lul.inventory.web.manager.WebManagerModuleAnchor
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.ApplicationPidFileWriter
import org.springframework.context.annotation.Bean

/**
 * @author justburrow
 * @since 2019-07-10
 */
@SpringBootApplication(scanBasePackageClasses = [WebManagerModuleAnchor::class])
class InventoryWebManagerRunner {
    @Bean
    fun timeProvider(): TimeProvider = MillisecondSystemTimeProvider()

    @Bean
    fun dialect() = Nl2brDialect()
}

internal val log = LoggerFactory.getLogger(InventoryWebManagerRunner::class.java)!!

fun main(args: Array<String>) {
    val app = SpringApplication(InventoryWebManagerRunner::class.java)
    app.addListeners(ApplicationPidFileWriter())
    val ctx = app.run(*args)

    if (log.isInfoEnabled) {
        ctx.beanDefinitionNames.forEach {
            log.info("bean : $it=${ctx.getBean(it)}")
        }
    }
}
