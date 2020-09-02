package com.example.globallogic.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.globallogic.data.repository.MainRepository
import com.example.globallogic.utils.Resource
import kotlinx.coroutines.Dispatchers

class ListViewModel (private val mainRepository: MainRepository)  : ViewModel() {

    fun getItems() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getItems()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}