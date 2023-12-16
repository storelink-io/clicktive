package com.clicktive.domains.api.data.dto.member

import com.clicktive.framework.springframework.annotation.NoArg
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@NoArg
data class TokenResponse (
    @JsonProperty("token")
    val accessToken: String,
    @JsonIgnore
    val tokenDueDt: Date,
    @JsonIgnore
    val refreshToken: String,
    @JsonIgnore
    val refreshTokenDueDt: Date
)

@NoArg
data class TokenSwaggerResponse (
    @JsonProperty("access_token")
    val accessToken: String,
    val tokenDueDt: Date,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    val refreshTokenDueDt: Date,
    @JsonProperty("expires_in")
    var expiresIn: Long
)