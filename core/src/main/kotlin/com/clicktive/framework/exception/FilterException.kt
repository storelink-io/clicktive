package com.clicktive.framework.exception

import com.clicktive.framework.api.ApiResponse
import com.clicktive.framework.api.getMessage
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class FilterException : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class, ServiceException::class)
    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse)
        } catch (ex: ServiceException) {
            fireServiceException(httpServletRequest, httpServletResponse, ex)
        } catch (ex1: ServletException){
            if (ex1.cause is ServiceException) {
                val ex = ex1.cause as ServiceException
                fireServiceException(httpServletRequest, httpServletResponse, ex)
            } else {
                ex1.printStackTrace()
                fireException(httpServletRequest, httpServletResponse, ex1.message!!)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            fireException(httpServletRequest, httpServletResponse, ex.message!!)
        }
    }

    fun fireServiceException(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        serviceException: ServiceException
    ) {
        val apiResponse = ApiResponse(
            data = mapOf("content" to null),
            messageCode = serviceException.messageCode,
            message     = serviceException.message
        )

        if (serviceException.messageCode == "AUTH-001") {
            apiResponse.status         = HttpStatus.UNAUTHORIZED.value()
            httpServletResponse.status = HttpStatus.UNAUTHORIZED.value()
        } else if (serviceException.messageCode.contains("ERR")) {
            apiResponse.status         = HttpStatus.INTERNAL_SERVER_ERROR.value()
            httpServletResponse.status = HttpStatus.OK.value()
        }

        httpServletResponse.contentType       = "application/json;charset=UTF-8"
        httpServletResponse.characterEncoding = "UTF-8"

        try {
            val json: String = apiResponse.convertToJson()
            httpServletResponse.writer.write(json)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun fireException(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        exceptionMessage: String
    ) {
        val apiResponse: ApiResponse<Nothing> = ApiResponse(
            data = mapOf("content" to null),
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            messageCode = "ERR-001",
            message = getMessage("ERR-001")
        )

        apiResponse.detailMessage  = exceptionMessage
        httpServletResponse.status = HttpStatus.INTERNAL_SERVER_ERROR.value()

        httpServletResponse.contentType       = "application/json;charset=UTF-8"
        httpServletResponse.characterEncoding = "UTF-8"

        if (exceptionMessage?.contains("authUser") == true) {
            // 사용자 인증 정보에 접근하려는데 오류가 난 경우
            apiResponse.status         = HttpStatus.UNAUTHORIZED.value()
            apiResponse.messageCode    = "AUTH-001"
            apiResponse.message        = getMessage("AUTH-001")
            httpServletResponse.status = HttpStatus.UNAUTHORIZED.value()
        }

        try {
            val respJson: String = apiResponse.convertToJson()
            httpServletResponse.writer.write(respJson)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}