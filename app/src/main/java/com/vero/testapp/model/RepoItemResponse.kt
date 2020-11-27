package com.vero.testapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoItemResponse (
    @field:Json(name = "items") val items: List<RepoItem>
)