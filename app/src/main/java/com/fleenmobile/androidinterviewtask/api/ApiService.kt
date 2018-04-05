package com.fleenmobile.androidinterviewtask.api

import com.fleenmobile.androidinterviewtask.data.Recipe
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getRecipesListDetailed")
    fun recipes(
            @Query("tags") tags: String = "",
            @Query("size") size: String = "thumbnail-medium",
            @Query("ratio") ratio: String = "1",
            @Query("limit") limit: Int = 50,
            @Query("from") from: Int = 0
    ): Observable<List<Recipe>>
}