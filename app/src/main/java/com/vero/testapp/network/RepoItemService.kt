package com.vero.testapp.network

import com.vero.testapp.model.RepoItemResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoItemService {

    @GET("/search/repositories")
    fun fetchRepoItemList(
        @Query("q") q : String,
        @Query("per_page") per_page : Int,
        @Query("page") page: Int,
        @Query("sort") sort : String = "stars"
    ) : Observable<RepoItemResponse>
}