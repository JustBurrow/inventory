package kr.lul.inventory.web.manager

import kr.lul.inventory.business.BusinessModuleAnchor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author justburrow
 * @since 2019-07-10
 */
@Configuration
@ComponentScan(basePackageClasses = [BusinessModuleAnchor::class])
class WebManagerModuleConfiguration
