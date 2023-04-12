package com.example.kishath.core.mapper

import com.example.kishath.core.network.exceptions.CorruptEntityException

open class DataMapper {
    fun fail(entity: Any): Nothing {
        throw CorruptEntityException("${entity::class.java} cannot be mapped to the result")
    }
}
