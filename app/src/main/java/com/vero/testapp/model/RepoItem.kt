package com.vero.testapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoItem (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "stargazers_count") val stars: Int
)