package com.clicktive.framework.springframework.annotation

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DescriptionFromEntity(
    val entity: KClass<*>,
)