package kr.lul.inventory.web.manager.configuration

import kr.lul.inventory.web.manager.mapping.IndexMvc
import kr.lul.inventory.web.manager.support.LoggingHandlerInterceptor
import kr.lul.inventory.web.manager.support.ManagerDetailsHandlerMethodArgumentResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.HiddenHttpMethodFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Configuration
@EnableWebMvc
class WebMvcConfiguration : WebMvcConfigurer {
    @Autowired
    private lateinit var loggingHandlerInterceptor: LoggingHandlerInterceptor
    @Autowired
    private lateinit var managerDetailsHandlerMethodArgumentResolver: ManagerDetailsHandlerMethodArgumentResolver

    @Bean
    fun hiddenHttpMethodFilter() = HiddenHttpMethodFilter()

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

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(managerDetailsHandlerMethodArgumentResolver)
    }
}
