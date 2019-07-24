package kr.lul.inventory.test

import kr.lul.inventory.data.DataModuleAnchor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author justburrow
 * @since 2019-07-23
 */
@Configuration
@ComponentScan(basePackageClasses = [DataModuleAnchor::class])
class TestUtilModuleConfiguration
