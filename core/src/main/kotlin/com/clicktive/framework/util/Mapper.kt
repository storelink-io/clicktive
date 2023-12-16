package com.clicktive.framework.util

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import java.util.stream.Collectors

object Mapper {
    val modelMapper = ModelMapper().apply {
        configuration.matchingStrategy = MatchingStrategies.STRICT
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }

    inline fun <reified T> map(modelMapper: ModelMapper, entity: Any): T = modelMapper.map(entity, T::class.java)

    inline fun <reified T> mapAll(modelMapper: ModelMapper, entityList: Collection<*>): List<T> =
        entityList.stream().map { entity -> modelMapper.map(entity, T::class.java) }.collect(Collectors.toList())

    inline fun <reified T> convert(entity: Any): T = map(modelMapper, entity)

    inline fun <reified T> convertAll(entityList: Collection<*>): List<T> = mapAll(modelMapper, entityList)
}