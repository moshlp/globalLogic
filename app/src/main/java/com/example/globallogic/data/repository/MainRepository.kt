package com.example.globallogic.data.repository

import com.example.globallogic.data.api.ApiHelper


class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getItems() = apiHelper.getItems()

}


