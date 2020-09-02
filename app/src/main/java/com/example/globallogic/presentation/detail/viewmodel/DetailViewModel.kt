package com.example.globallogic.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.globallogic.domain.ItemResponse

class DetailViewModel  : ViewModel() {

    var item : MutableLiveData<ItemResponse> = MutableLiveData()

    fun setItem(item : ItemResponse){
        this.item.postValue(item)
    }

}