package com.quintallabs.data.mappers

import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbGenre
import com.quintallabs.domain.models.Genre
import javax.inject.Inject

class TmdbGenreToGenreMapper
@Inject
constructor() : Mapper<TmdbGenre, Genre> {

    override fun map(input: TmdbGenre): Genre =
        Genre(input.id, input.name)

}