package com.clicktive.controller

import com.clicktive.framework.springframework.base.BaseController
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ctv/v1/ad")
class AdController(

) : BaseController() {
    @Operation(summary = "성과 정보 저장")
    @GetMapping("/result")
    fun saveResult() {

    }

    @Operation(summary = "업로드 파일 상태 조회")
    @PostMapping("/result")
    fun getResult() {

    }
}