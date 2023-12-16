package com.clicktive.domains.api.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class JpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ctv")
    fun dataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ctv.hikari")
    fun hikariDataSource(
        dataSourceProperties: DataSourceProperties
    ): HikariDataSource {
        return dataSourceProperties
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Primary
    @Bean
    fun jpaEntityManagerFactory(
        hikariDataSource: DataSource,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(hikariDataSource)
            .packages(
                "com.clicktive.domains.api.data.entity"
            )
            .build()
    }

//    @Bean
//    fun jpaTransactionManager(
//        jpaEntityManagerFactory: LocalContainerEntityManagerFactoryBean
//    ): PlatformTransactionManager {
//        val transactionManager = JpaTransactionManager()
//        transactionManager.entityManagerFactory = jpaEntityManagerFactory.getObject()
//        return transactionManager
//    }
    @Primary
    @Bean
    fun jpaTransactionManager(
        hikariDataSource: DataSource,
        jpaEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
//            val dataSourceTransactionManager = DataSourceTransactionManager(hikariDataSource)
        val jpaTransactionManager = JpaTransactionManager(jpaEntityManagerFactory.`object`!!)
        return jpaTransactionManager
    }
}