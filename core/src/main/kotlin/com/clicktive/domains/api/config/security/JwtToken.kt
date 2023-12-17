package com.clicktive.domains.api.config.security

import java.util.*

data class JwtToken(
    val token: String,
    val dueDt: Date
)