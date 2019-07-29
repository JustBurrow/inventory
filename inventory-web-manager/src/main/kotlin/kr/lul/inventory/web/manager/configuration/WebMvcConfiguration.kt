package kr.lul.inventory.web.manager.configuration

import kr.lul.inventory.web.manager.mapping.IndexMvc
import kr.lul.inventory.web.manager.support.LoggingHandlerInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Configuration
@EnableWebMvc
class WebMvcConfiguration : WebMvcConfigurer {
    @Autowired
    private lateinit var loggingHandlerInterceptor: LoggingHandlerInterceptor

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.springframework.web.servlet.config.annotation.WebMvcConfigurer
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:./front/assets/")
        registry.addResourceHandler("/libs/**")
                .addResourceLocations("file:./front/libs/")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController(IndexMvc.C.FULL_API_SIGN_IN)
                .setViewName(IndexMvc.V.SIGN_IN)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loggingHandlerInterceptor)
    }
}
