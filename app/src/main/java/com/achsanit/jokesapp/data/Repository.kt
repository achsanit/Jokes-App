package com.achsanit.jokesapp.data

import com.achsanit.jokesapp.data.network.ApiService
import com.achsanit.jokesapp.data.response.ResultItem
import com.achsanit.jokesapp.utils.Resource
import com.achsanit.jokesapp.utils.resourceMapper

class Repository(
    private val apiService: ApiService
) {

    suspend fun listJokes(query: String): Resource<List<ResultItem?>?> {
        val params = HashMap<String, String>()
        params["query"] = query

        return resourceMapper {
            apiService.jokes(params).result
        }
    }

    suspend fun randomJoke(): Resource<ResultItem?> {
        return resourceMapper {
            apiService.randomJoke()
        }
    }

}