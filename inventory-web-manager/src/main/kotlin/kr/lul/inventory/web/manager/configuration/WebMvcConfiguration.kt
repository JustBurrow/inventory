package kr.lul.inventory.web.manager.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Configuration
@EnableWebMvc
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:./front/assets/")
        registry.addResourceHandler("/libs/**")
                .addResourceLocations("file:./front/libs/")
    }
}
