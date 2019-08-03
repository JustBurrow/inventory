package kr.lul.inventory.web.manager.support

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.net.URL
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author justburrow
 * @since 2019-07-29
 */
@Component
class LoggingHandlerInterceptor : HandlerInterceptorAdapter() {
    private val log = LoggerFactory.getLogger(LoggingHandlerInterceptor::class.java)!!

    private val EXCLUDE_PREFIX = setOf("/assets/", "/libs/")

    private fun logRequest(request: HttpServletRequest) {
        val url = URL(request.requestURL.toString())

        for (prefix: String in EXCLUDE_PREFIX) {
            if (url.path.startsWith(prefix))
                return
        }

        log.info("url=$url, sessionId=${request.session.id}")
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.springframework.web.servlet.handler.HandlerInterceptorAdapter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (log.isTraceEnabled)
            log.trace("args : request=$request, response=$response, handler=$handler")

        if (log.isInfoEnabled)
            logRequest(request)

        return true
    }
}
