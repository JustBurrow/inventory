package kr.lul.inventory.web.manager.configuration

import kr.lul.inventory.web.manager.mapping.AssetMvc
import kr.lul.inventory.web.manager.mapping.IndexMvc
import kr.lul.inventory.web.manager.mapping.LibMvc
import kr.lul.inventory.web.manager.mapping.ManagerMvc
import kr.lul.inventory.web.manager.support.ManagerDetailsService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder


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

    @Autowired
    private lateinit var managerDetailsService: ManagerDetailsService
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

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
                .logoutUrl(IndexMvc.C.FULL_API_SIGN_OUT)
                .logoutSuccessUrl(IndexMvc.C.FULL_API_INDEX)

        http.authorizeRequests()
                .antMatchers(*PUBLIC_PATHS.toTypedArray()).permitAll()
                .antMatchers("/**").authenticated()
    }

    override fun configure(builder: AuthenticationManagerBuilder?) {
        if (log.isTraceEnabled)
            log.trace("args : builder={}", builder)
        builder ?: throw IllegalArgumentException("builder is null.")

        builder.userDetailsService(managerDetailsService)
                .passwordEncoder(passwordEncoder)
    }
}
