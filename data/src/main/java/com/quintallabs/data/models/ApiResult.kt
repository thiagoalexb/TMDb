package com.quintallabs.data.models

import com.fasterxml.jackson.annotation.JsonProperty

class ApiResult<T>(

    val pages: Long,
    val results: T,

    @JsonProperty("total_pages")
    val totalPages: Long,

    @JsonProperty("total_results")
    val totalResults: Long
) {
}