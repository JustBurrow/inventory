package kr.lul.inventory.runner.web.manager

import kr.lul.inventory.web.manager.WebManagerModuleAnchor
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.ApplicationPidFileWriter

/**
 * @author justburrow
 * @since 2019-07-10
 */
@SpringBootApplication(scanBasePackageClasses = [WebManagerModuleAnchor::class])
class InventoryWebManagerRunner

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
