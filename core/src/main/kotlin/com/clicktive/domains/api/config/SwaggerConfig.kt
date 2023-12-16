package com.clicktive.domains.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class SwaggerConfig {
//    @Bean
//    fun openAPI(): OpenAPI {
//        return OpenAPI()
//            .info(
//                Info().title("CLICKTIVE 광고 성과 시스템 API")
//                    .description("클릭티브 광고 성과 시스템 API")
//                    .version("v0.0.1")
//            )
//    }

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .addSecurityItem(SecurityRequirement().addList("JWT"))
            .components(Components().addSecuritySchemes("JWT", createAPIKeyScheme()))
            .info(
                Info().title("CLICKTIVE 광고 성과 시스템 API")
                    .description("클릭티브 광고 성과 시스템 API")
                    .version("v0.0.1")
            )
    }

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
    }
}