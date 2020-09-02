package com.example.globallogic.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getItems() = apiService.getItems()

}