package com.example.globallogic.data.api


import com.example.globallogic.domain.ItemResponse
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getItems(): List<ItemResponse>
}