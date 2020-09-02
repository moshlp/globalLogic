package com.example.globallogic.di

import com.example.demogeopagos.data.api.RetrofitBuilder
import com.example.globallogic.data.api.ApiHelper
import com.example.globallogic.data.repository.MainRepository
import com.example.globallogic.presentation.list.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val globalLogicModule = module {
    viewModel { ListViewModel(get()) }


    single {
        MainRepository(get())
    }

    single {
        ApiHelper(get())
    }

    single {
        RetrofitBuilder.apiService
    }

}

val globalLogicApp = listOf(globalLogicModule)