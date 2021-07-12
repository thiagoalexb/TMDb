package com.quintallabs.data.mappers.base

import javax.inject.Inject

class ListMapperImp<I, O>
@Inject
constructor(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {

    override fun map(input: List<I>): List<O> =
        input.map { mapper.map(it) }
}