package com.achsanit.jokesapp.data.network

import com.achsanit.jokesapp.data.response.JokesResponse
import com.achsanit.jokesapp.data.response.ResultItem
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("search")
    suspend fun jokes(@QueryMap params: HashMap<String, String>): JokesResponse

    @GET("random")
    suspend fun randomJoke(): ResultItem
}