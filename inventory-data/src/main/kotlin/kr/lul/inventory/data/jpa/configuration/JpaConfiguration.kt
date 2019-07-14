package kr.lul.inventory.data.jpa.configuration

import kr.lul.inventory.data.jpa.JpaAnchor
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
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * @author justburrow
 * @since 2019-07-06
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = [JpaAnchor::class])
@EnableTransactionManagement
class JpaConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource() = DataSourceBuilder.create().build()

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setDatabase(Database.MYSQL)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource()
        factory.setPackagesToScan(JpaAnchor.PACKAGE_NAME)
        factory.jpaVendorAdapter = adapter

        return factory
    }

    @Bean
    fun transactionManager() = JpaTransactionManager(entityManagerFactory().getObject()!!)

    @Bean
    fun hibernateExceptionTranslator() = HibernateExceptionTranslator()
}
