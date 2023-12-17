package com.clicktive.controller

import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
class IndexController(
    private val env: Environment
) : BaseController() {
    @Operation(summary = "health check")
    @GetMapping("/health")
    fun health(): String {
        return "[clicktive/" + (env?.getProperty("spring.profiles.active") ?: "health") + "] " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString()
    }
}