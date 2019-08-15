package kr.lul.inventory.web.manager.exception

import kr.lul.inventory.web.manager.mapping.ErrorMvc.M
import kr.lul.inventory.web.manager.mapping.ErrorMvc.V
import org.slf4j.LoggerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author justburrow
 * @since 2019-08-15
 */
@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)!!

    private fun handle(e: Throwable, model: Model): String {
        model.addAttribute(M.ERROR, e)
        model.addAttribute(M.CAUSE, e.cause)

        return V.STATUS_500
    }

    @ExceptionHandler(RuntimeException::class)
    fun handle(e: RuntimeException, model: Model): String {
        log.warn(e.message, e)

        handle(e as Exception, model)

        return V.STATUS_500
    }

    @ExceptionHandler(NotFoundException::class)
    fun handle(e: NotFoundException, model: Model): String {
        log.warn(e.message, e)

        handle(e as Exception, model)

        return V.STATUS_404
    }
}
