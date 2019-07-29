package kr.lul.inventory.web.manager.support

import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


/**
 * @author justburrow
 * @since 2019-07-29
 */
@Component
class ManagerDetailsHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {
    private val log = LoggerFactory.getLogger(ManagerDetailsHandlerMethodArgumentResolver::class.java)!!

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.springframework.web.method.support.HandlerMethodArgumentResolver
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun supportsParameter(param: MethodParameter): Boolean {
        if (log.isTraceEnabled)
            log.trace("args : param=$param, param.type=${param.genericParameterType}")

        return param.genericParameterType == ManagerDetails::class.java
    }

    override fun resolveArgument(
            param: MethodParameter,
            mav: ModelAndViewContainer?,
            request: NativeWebRequest,
            binder: WebDataBinderFactory?
    ): Any? {
        if (log.isTraceEnabled)
            log.trace("args : param=$param, mav=$mav, request=$request, binder=$binder")

        val authentication = SecurityContextHolder.getContext().authentication

        val manager = if (
                null === authentication ||
                null === authentication.principal ||
                authentication.principal !is ManagerDetails
        ) {
            null
        } else {
            authentication.principal as ManagerDetails
        }

        if (log.isTraceEnabled)
            log.trace("return : $manager")
        return manager
    }
}
