package com.clicktive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(
    scanBasePackages=["com.clicktive", "com.clicktive.domains.api", "com.clicktive.framework"]
)
@EnableJpaRepositories(
    basePackages = ["com.clicktive.domains.api.repository"],
    entityManagerFactoryRef = "jpaEntityManagerFactory"
)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}