package kr.lul.inventory.business

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author justburrow
 * @since 2019-07-08
 */
@Configuration
@ComponentScan(basePackageClasses = [DataModuleAnchor::class])
class BusinessModuleConfiguration {
}
