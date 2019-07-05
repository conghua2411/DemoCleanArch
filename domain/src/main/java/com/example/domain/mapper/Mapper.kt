package com.example.domain.mapper

internal interface Mapper<E,M> {
    fun mapEntityToModel(e: E): M
    fun mapEntityToModel(e: List<E>): List<M>
    fun mapModelToEntity(m: M): E
    fun mapModelToEntity(m: List<M>): List<E>
}