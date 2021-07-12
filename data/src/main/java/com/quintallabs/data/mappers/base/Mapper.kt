package com.quintallabs.data.mappers.base

interface Mapper<I, O> {
    fun map(input: I): O
}