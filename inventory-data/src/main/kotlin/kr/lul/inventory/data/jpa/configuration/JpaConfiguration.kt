package kr.lul.inventory.data.jpa.configuration

import kr.lul.inventory.data.jpa.JpaAnchor
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.HibernateExceptionTranslator
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


/**
 * @author justburrow
 * @since 2019-07-06
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = [JpaAnchor::class])
@EntityScan(basePackageClasses = [JpaAnchor::class])
@EnableTransactionManagement
class JpaConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setDatabase(Database.MYSQL)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource()
        factory.jpaVendorAdapter = adapter

        return factory
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory().getObject()!!)
    }

    @Bean
    fun hibernateExceptionTranslator(): HibernateExceptionTranslator {
        return HibernateExceptionTranslator()
    }
}
