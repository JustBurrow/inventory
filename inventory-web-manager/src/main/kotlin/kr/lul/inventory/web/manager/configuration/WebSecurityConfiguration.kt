package kr.lul.inventory.web.manager.configuration

import kr.lul.inventory.web.manager.mapping.AssetMvc
import kr.lul.inventory.web.manager.mapping.IndexMvc
import kr.lul.inventory.web.manager.mapping.LibMvc
import kr.lul.inventory.web.manager.mapping.ManagerMvc
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @author justburrow
 * @since 2019-07-14
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    private val log = LoggerFactory.getLogger(WebSecurityConfiguration::class.java)!!
    private val PUBLIC_PATHS = listOf(IndexMvc.C.FULL_API_INDEX,
            IndexMvc.C.FULL_API_SIGN_UP, IndexMvc.C.FULL_API_SIGN_IN,
            ManagerMvc.C.FULL_API_CREATE, ManagerMvc.C.FULL_API_CREATE_FORM,
            "${LibMvc.C.GROUP}/**", "${AssetMvc.C.GROUP}/**")

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun configure(http: HttpSecurity?) {
        if (null == http)
            throw IllegalArgumentException("http is null.")

        if (log.isInfoEnabled)
            log.info("PUBLIC_PATHS={}", PUBLIC_PATHS)

        http.formLogin()
                .loginPage(IndexMvc.C.FULL_API_SIGN_IN)
                .defaultSuccessUrl(IndexMvc.C.FULL_API_INDEX)
        http.logout()
                .logoutSuccessUrl(IndexMvc.C.FULL_API_INDEX)

        http.authorizeRequests()
                .antMatchers(*PUBLIC_PATHS.toTypedArray()).anonymous()
                .antMatchers("/**").authenticated()
    }
}
