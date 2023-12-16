package com.clicktive.domains.api.config.security

import com.clicktive.framework.exception.FilterException
import jakarta.validation.constraints.NotNull
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtAuthenticationFilter: JwtAuthenticationFilter,
    val filterException: FilterException
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? = http
        .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
        .csrf { it.disable() }
        .cors(Customizer.withDefaults())
        .authorizeHttpRequests {
            it
                .requestMatchers("/ctv/v1/members/sign-up").permitAll()
                .requestMatchers("/ctv/v1/members/sign-in").permitAll()
                .anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        .addFilterBefore(filterException, JwtAuthenticationFilter::class.java)
        .build()

    @Bean
    fun customWebSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer {
            it.ignoring()
                .requestMatchers("/swagger-ui/**")
                .requestMatchers("/v3/api-docs/**")
        }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedHeaders = arrayListOf("*")
        configuration.allowedMethods = arrayListOf("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        configuration.allowCredentials = true
        configuration.allowedOrigins = arrayListOf("*")
//        configuration.allowedOrigins = listOf(
//            "http://localhost:3000",
//            "https://www.clicktive.kr",
//            "https://api.clicktive.kr",
//            "https://dev.clicktive.kr",
//            "https://apid.clicktive.kr",
//        )
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}

