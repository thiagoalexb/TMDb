package com.quintallabs.data.mappers

import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbGenre
import com.quintallabs.domain.models.Genre
import javax.inject.Inject

class GenreToTmdbGenreMapper
@Inject
constructor() : Mapper<Genre, TmdbGenre> {

    override fun map(input: Genre): TmdbGenre =
        TmdbGenre(input.id, input.name)
}