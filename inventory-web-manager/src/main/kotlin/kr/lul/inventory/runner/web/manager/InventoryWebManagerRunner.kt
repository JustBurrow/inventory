package kr.lul.inventory.runner.web.manager

import kr.lul.inventory.web.manager.WebManagerModuleAnchor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.ApplicationPidFileWriter

/**
 * @author justburrow
 * @since 2019-07-10
 */
@SpringBootApplication(scanBasePackageClasses = [WebManagerModuleAnchor::class])
class WebManagerRunner

fun main(args: Array<String>) {
    val app = SpringApplication(WebManagerRunner::class.java)
    app.addListeners(ApplicationPidFileWriter())
    val ctx = app.run(*args)
}
